package com.findork.preclinical.features.confirmationtoken;


import com.findork.preclinical.security.constants.AuthConstants;
import com.findork.preclinical.exceptions.InvalidConfirmTokenException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken findConfirmationTokenByValue(String value) {
        return confirmationTokenRepository.findByValue(value)
                .orElseThrow(() -> new InvalidConfirmTokenException(AuthConstants.INVALID_CONFIRMATION_TOKEN));
    }

    public void deleteTokenAfterConfirmation(String confirmationToken) {
        confirmationTokenRepository.deleteConfirmationTokenByValue(confirmationToken);
    }
}
