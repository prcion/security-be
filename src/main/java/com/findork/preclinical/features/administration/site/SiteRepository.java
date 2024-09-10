package com.findork.preclinical.features.administration.site;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SiteRepository extends MongoRepository<Site, String> {
    List<Site> findAllByCompanyId(String companyId);
}
