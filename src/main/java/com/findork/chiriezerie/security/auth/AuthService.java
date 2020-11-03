package com.findork.chiriezerie.security.auth;

import com.findork.chiriezerie.exception.AlreadyExistsException;
import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.feature.account.UserRepository;
import com.findork.chiriezerie.security.constants.AuthConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
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
}
