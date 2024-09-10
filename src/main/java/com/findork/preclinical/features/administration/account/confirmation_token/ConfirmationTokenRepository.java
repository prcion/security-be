package com.findork.preclinical.features.administration.account.confirmation_token;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    void deleteConfirmationTokenByValue(String confirmationToken);

    Optional<ConfirmationToken> findByValue(String value);
}
