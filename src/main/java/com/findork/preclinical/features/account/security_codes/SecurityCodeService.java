package com.findork.preclinical.features.account.security_codes;

import com.findork.preclinical.exceptions.ValidationException;
import com.findork.preclinical.features.account.domain.User;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityCodeService {
    private final SecurityCodeRepository securityCodeRepository;

    public SecurityCode createSecurityCode(User user) {
        String randomCode = RandomStringUtils.randomNumeric(7);
        var securityCode = SecurityCode
                .builder()
                .userId(user.getId())
                .code(randomCode)
                .build();

        return securityCodeRepository.save(securityCode);
    }

    public void validate(User user, String securityCode) {
        var securityCodeOptional = securityCodeRepository.findByUserIdAndCode(user.getId(), securityCode);
        if (securityCodeOptional.isEmpty()) {
            throw new ValidationException("Security code invalid");
        }
    }
}
