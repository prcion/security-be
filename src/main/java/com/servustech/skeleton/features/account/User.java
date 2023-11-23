package com.servustech.skeleton.features.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "users")
public class User{
    @Id
    private String id;

    private String name;

    private String username;

    private String email;

    private String password;

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private String role;

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
