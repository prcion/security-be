package com.findork.preclinical.features.confirmationtoken;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByUserEmail(@Param("email") String email);

    void deleteConfirmationTokenByValue(@Param("confirmationToken") String confirmationToken);
}
