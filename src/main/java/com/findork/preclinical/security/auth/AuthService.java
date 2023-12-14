package com.findork.preclinical.security.auth;

import com.findork.preclinical.exceptions.AlreadyExistsException;
import com.findork.preclinical.security.constants.AuthConstants;
import com.findork.preclinical.security.payload.ChangePasswordRequest;
import com.findork.preclinical.features.account.AccountStatus;
import com.findork.preclinical.features.account.User;
import com.findork.preclinical.features.account.UserRepository;
import com.findork.preclinical.features.confirmationtoken.ConfirmationTokenService;
import com.findork.preclinical.utils.PasswordEncoderUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    public void verifyIfEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException(AuthConstants.USERNAME_OR_EMAIL_EXIST);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void validateTokenAndSetUserStatusToActive(String confirmationToken, String email) {
        var confirmationTokenDB = confirmationTokenService.validateToken(email, confirmationToken);

        var user = confirmationTokenDB.getUser();

        user.setAccountStatus(AccountStatus.ACTIVE);

        userRepository.save(user);

        confirmationTokenService.deleteTokenAfterConfirmation(confirmationToken);
    }


    public void changeUserPassword(ChangePasswordRequest request, User user) {

        PasswordEncoderUtils.verifyMatchingPasswords(request.getOldPassword(), user.getPassword());

        user.setPassword(PasswordEncoderUtils.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}
