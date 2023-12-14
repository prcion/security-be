package com.findork.preclinical.features.company.aware;

import com.findork.preclinical.features.commons.BaseDocument;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@EnableMongoAuditing
public class CompanyAwareDocument extends BaseDocument {

    // foreign key to Company
    @Field(name = "company_id", targetType = FieldType.OBJECT_ID)
    @NotNull
    private String companyId;
}
