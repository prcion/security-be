package com.findork.preclinical.features.account.security_codes;

import com.findork.preclinical.features.commons.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "security_codes")
public class SecurityCode extends BaseDocument {
    private String code;

    @Field(name = "user_id", targetType = FieldType.OBJECT_ID)
    private String userId;
}
