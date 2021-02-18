package com.servustech.skeleton.features.confirmationtoken;


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

    public Optional<ConfirmationToken> findConfirmationTokenByEmail(String email){

        return confirmationTokenRepository.findByUserEmail(email);
    }

    @Transactional
    public void deleteTokenAfterConfirmation(String confirmationToken){
        confirmationTokenRepository.deleteConfirmationTokenByValue(confirmationToken);
    }
}