package com.servustech.skeleton.features.account;

import com.servustech.skeleton.features.commons.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "users")
public class User extends BaseDocument {

    private String name;

    private String email;

    private String password;

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private AccountType accountType;

    @Field(name = "company_id")
    private String companyId;
}
