package com.findork.preclinical.features.account.domain;

public enum AccountType {
    ROLE_USER, ROLE_COMPANY_ADMINISTRATOR, ROLE_SYSTEM_ADMINISTRATOR;

    public boolean isSystemAdministrator() {
        return this == ROLE_SYSTEM_ADMINISTRATOR;
    }
}
