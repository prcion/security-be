package com.servustech.skeleton.security.auth;

import com.servustech.skeleton.exception.AlreadyExistsException;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


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
    public void validateToken(String confirmationToken, Optional<ConfirmationToken> confirmationTokenOptional) {


        ConfirmationToken confirmationTokenObj = confirmationTokenOptional.orElseThrow(
                () -> new InvalidConfirmTokenException(AuthConstants.INVALID_CONFIRMATION_TOKEN)
        );

        if (!confirmationTokenObj.getValue().equals(confirmationToken)) {

            throw new InvalidConfirmTokenException(AuthConstants.INVALID_CONFIRMATION_TOKEN);

        }

        User user = confirmationTokenObj.getUser();

        user.setAccountStatus(AccountStatus.ACTIVE);

    }




}
