package com.servustech.skeleton.security.auth;

import com.servustech.skeleton.exception.AlreadyExistsException;
import com.servustech.skeleton.exception.CustomException;
import com.servustech.skeleton.exception.InvalidConfirmTokenException;
import com.servustech.skeleton.features.account.AccountStatus;
import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.features.account.UserRepository;
import com.servustech.skeleton.features.confirmationtoken.ConfirmationToken;
import com.servustech.skeleton.features.confirmationtoken.ConfirmationTokenRepository;
import com.servustech.skeleton.features.confirmationtoken.ConfirmationTokenService;
import com.servustech.skeleton.security.constants.AuthConstants;
import com.servustech.skeleton.utils.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


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

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsernameOrEmail(String userNameOrEmail) {
        return userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail)
                .orElseThrow(() -> new AlreadyExistsException(AuthConstants.USERNAME_OR_EMAIL_EXIST));
    }


    @Transactional
    public void validateTokenAndSetUserStatusToActive(String confirmationToken, String email) {

        ConfirmationToken confirmationTokenDB = confirmationTokenService.validateToken(email, confirmationToken);

        User user = confirmationTokenDB.getUser();

        user.setAccountStatus(AccountStatus.ACTIVE);

        confirmationTokenService.deleteTokenAfterConfirmation(confirmationToken);

    }


    @Transactional
    public void changeUserPassword(ChangePasswordRequest request) {

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new CustomException("Username not found!"));

        PasswordEncoderUtils.verifyMatchingPasswords(request.getOldPassword(), user.getPassword());


        user.setPassword(PasswordEncoderUtils.encode(request.getNewPassword()));

    }
}
