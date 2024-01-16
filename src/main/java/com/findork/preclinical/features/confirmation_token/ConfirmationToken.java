package com.findork.preclinical.features.confirmation_token;

import com.findork.preclinical.features.account.domain.User;
import com.findork.preclinical.features.commons.BaseDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static org.springframework.data.mongodb.core.mapping.FieldType.OBJECT_ID;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "confirmation_tokens")
public class ConfirmationToken extends BaseDocument {

    private String value;

    @Field(value = "user_id", targetType = OBJECT_ID)
    private String userId;

    public ConfirmationToken(String value, User account) {
        this.value = value;
        this.userId = account.getId();
    }
}
