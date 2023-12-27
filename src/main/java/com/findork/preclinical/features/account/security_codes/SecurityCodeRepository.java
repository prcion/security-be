package com.findork.preclinical.features.account.security_codes;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SecurityCodeRepository extends MongoRepository<SecurityCode, String> {

    Optional<SecurityCode> findByUserIdAndCode(String userId, String securityCode);

    void deleteAllByUserId(Long userId);
}
