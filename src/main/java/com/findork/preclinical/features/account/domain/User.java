package com.findork.preclinical.features.account.domain;

import com.findork.preclinical.features.commons.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "users")
public class User extends BaseDocument {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private AccountType accountType;

    private boolean allowTwoStepAuthentication;

    @Field(name = "company_id", targetType = FieldType.OBJECT_ID)
    private String companyId;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
