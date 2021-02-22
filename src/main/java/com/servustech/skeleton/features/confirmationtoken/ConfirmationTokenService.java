package com.servustech.skeleton.features.confirmationtoken;


import com.servustech.skeleton.exception.InvalidConfirmTokenException;
import com.servustech.skeleton.security.constants.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken findConfirmationTokenByEmail(String email) {

        return confirmationTokenRepository.findByUserEmail(email).orElseThrow(() -> new InvalidConfirmTokenException(AuthConstants.INVALID_CONFIRMATION_TOKEN));
    }

    @Transactional
    public void deleteTokenAfterConfirmation(String confirmationToken) {
        confirmationTokenRepository.deleteConfirmationTokenByValue(confirmationToken);
    }

    public ConfirmationToken validateToken(String email, String confirmationTokenRequest) {

        ConfirmationToken confirmationToken = findConfirmationTokenByEmail(email);

        if (!confirmationToken.equals(confirmationTokenRequest)) {
            throw new InvalidConfirmTokenException(AuthConstants.INVALID_CONFIRMATION_TOKEN);
        }

        return confirmationToken;

    }
}
