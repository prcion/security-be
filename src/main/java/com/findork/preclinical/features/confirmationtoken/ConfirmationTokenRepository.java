package com.findork.preclinical.features.confirmationtoken;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    void deleteConfirmationTokenByValue(String confirmationToken);

    Optional<ConfirmationToken> findByValue(String value);
}
