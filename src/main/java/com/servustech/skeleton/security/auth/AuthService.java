package com.servustech.skeleton.security.auth;

import com.servustech.skeleton.exceptions.AlreadyExistsException;
import com.servustech.skeleton.features.account.AccountStatus;
import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.features.account.UserRepository;
import com.servustech.skeleton.features.confirmationtoken.ConfirmationTokenService;
import com.servustech.skeleton.security.constants.AuthConstants;
import com.servustech.skeleton.security.payload.ChangePasswordRequest;
import com.servustech.skeleton.utils.PasswordEncoderUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    public void verifyIfUsernameOrEmailExists(String username, String email) {
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException(AuthConstants.USERNAME_OR_EMAIL_EXIST);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void validateTokenAndSetUserStatusToActive(String confirmationToken, String email) {
        var confirmationTokenDB = confirmationTokenService.validateToken(email, confirmationToken);

        var user = confirmationTokenDB.getUser();

        user.setAccountStatus(AccountStatus.ACTIVE);

        confirmationTokenService.deleteTokenAfterConfirmation(confirmationToken);
    }


    @Transactional
    public void changeUserPassword(ChangePasswordRequest request, User user) {

        PasswordEncoderUtils.verifyMatchingPasswords(request.getOldPassword(), user.getPassword());

        user.setPassword(PasswordEncoderUtils.encode(request.getNewPassword()));
    }
}
