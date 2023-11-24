package com.servustech.skeleton.features.account;

import com.servustech.skeleton.features.commons.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;



@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "users")
public class User extends BaseDocument {


    private String name;

    private String username;

    private String email;

    private String password;

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private AccountType accountType;

    public boolean isActive() {
        return accountStatus.isActive();
    }

    public boolean isBanned() {
        return accountStatus.isBanned();
    }
    public boolean isInactive(){
        return accountStatus.isInactive();
    }

    public boolean isLocked() {
        return accountStatus.isLocked();
    }
}
