package com.findork.preclinical.security.auth;

import com.findork.preclinical.exceptions.AlreadyExistsException;
import com.findork.preclinical.exceptions.NotFoundException;
import com.findork.preclinical.exceptions.ValidationException;
import com.findork.preclinical.security.constants.AuthConstants;
import com.findork.preclinical.security.payload.ChangePasswordRequest;
import com.findork.preclinical.features.account.AccountStatus;
import com.findork.preclinical.features.account.User;
import com.findork.preclinical.features.account.UserRepository;
import com.findork.preclinical.features.confirmation_token.ConfirmationTokenService;
import com.findork.preclinical.security.payload.ConfirmationRequest;
import com.findork.preclinical.utils.PasswordEncoderUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    public void verifyIfEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException(AuthConstants.USERNAME_OR_EMAIL_EXIST);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void confirmUserAccount(ConfirmationRequest request) {

        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            throw new ValidationException("The passwords doesn't match");
        }

        var confirmationTokenDB = confirmationTokenService.findConfirmationTokenByValue(request.getConfirmationToken());

        var userOptional = userRepository.findById(confirmationTokenDB.getUserId());

        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        var user = userOptional.get();


        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        confirmationTokenService.deleteTokenAfterConfirmation(request.getConfirmationToken());
    }


    public void changeUserPassword(ChangePasswordRequest request, User user) {

        PasswordEncoderUtils.verifyMatchingPasswords(request.getOldPassword(), user.getPassword());

        user.setPassword(PasswordEncoderUtils.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}
