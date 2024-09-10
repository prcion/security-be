package com.findork.preclinical.features.administration.company;

import com.findork.preclinical.features.administration.commons.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "companies")
public class Company extends BaseDocument {

    private String name;

    private Boolean active;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
