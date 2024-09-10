package com.findork.preclinical.features.administration.site;

import com.findork.preclinical.features.administration.company.aware.CompanyAwareDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "sites")
public class Site extends CompanyAwareDocument {
    private boolean active;
    private String name;
    private String address1;
    private String address2;
    private String alias;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String timezone;
}
