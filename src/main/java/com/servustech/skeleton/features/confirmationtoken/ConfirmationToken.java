package com.servustech.skeleton.features.confirmationtoken;

import com.servustech.skeleton.features.account.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "confirmation_tokens")
public class ConfirmationToken {

    @Id
    private String id;

    private String value;

    @CreatedDate
    private LocalDateTime createdOn;

    @DBRef
    private User user;

    public ConfirmationToken(String value, User account) {
        this.value = value;
        this.user = account;
    }
}
