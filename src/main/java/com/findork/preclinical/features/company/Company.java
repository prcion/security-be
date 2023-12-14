package com.findork.preclinical.features.company;

import com.findork.preclinical.features.commons.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    private String website;
    private String billingAttnTo;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingState;
    private String billingZip;
    private String billingPhone;
    private String ein;

    @Field(name = "country_id")
    private String countryId;
}
