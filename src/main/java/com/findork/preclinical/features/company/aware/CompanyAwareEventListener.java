package com.findork.preclinical.features.company.aware;

import com.findork.preclinical.exceptions.PermissionDeniedException;
import com.findork.preclinical.features.commons.SecurityUtils;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.*;
import org.springframework.stereotype.Component;

/**
 * CompanyEventListener for the Documents associated with Company
 * we only use onBeforeConvert - that means when POJO is converted to Document we add company from the user to the field
 * also we use onBeforeLoad - that means whatever a user get something from db that will be verified
 * update and delete we cannot use because we cannot follow the previous companyId of Document, a solution for that is when update or delete a document use findById,
 * findById will verify if the companyId from db is the same with the user companyId
 * @author Ion Porcescu
 */
@Component
public class CompanyAwareEventListener extends AbstractMongoEventListener<CompanyAwareDocument> {

    @Override
    public void onBeforeConvert(@NotNull BeforeConvertEvent<CompanyAwareDocument> event) {
        if (SecurityUtils.getPrincipal().isPresent()) {
            if (!SecurityUtils.getAuthenticatedPrincipal().getAccountType().isSystemAdministrator()) {
                if (event.getSource().getCompanyId() == null) {
                    var companyId = SecurityUtils.getPrincipal().isEmpty() ? null : SecurityUtils.getAuthenticatedPrincipal().getCompanyId();

                    // if company is null we cannot create a document associated with company
                    if (companyId == null) {
                        throw new PermissionDeniedException("Missing companyId");
                    }
                    event.getSource().setCompanyId(companyId);
                }
            }
        }
    }

    @Override
    public void onBeforeSave(@NotNull BeforeSaveEvent<CompanyAwareDocument> event) {
        if (SecurityUtils.getPrincipal().isPresent()) {
            if (!SecurityUtils.getAuthenticatedPrincipal().getAccountType().isSystemAdministrator()) {
                var companyId = event.getSource().getCompanyId();

                // if company is null we cannot load a document associated with company
                if (companyId == null) {
                    throw new PermissionDeniedException("Missing companyId");
                }

                // verify if companyId from document is the same with the authenticated user
                if (!companyId.equals(SecurityUtils.getAuthenticatedPrincipal().getCompanyId())) {
                    throw new PermissionDeniedException("Not allowed to access/update/remove resource of another company.");
                }
            }
        }
    }

    @Override
    public void onBeforeDelete(@NotNull BeforeDeleteEvent<CompanyAwareDocument> event) {
        if (SecurityUtils.getPrincipal().isPresent()) {
            if (!SecurityUtils.getAuthenticatedPrincipal().getAccountType().isSystemAdministrator()) {
                var companyId = (ObjectId) event.getSource().get("company_id");

                // if company is null we cannot load a document associated with company
                if (companyId == null) {
                    throw new PermissionDeniedException("Missing companyId");
                }

                // verify if companyId from document is the same with the authenticated user
                if (!companyId.toString().equals(SecurityUtils.getAuthenticatedPrincipal().getCompanyId())) {
                    throw new PermissionDeniedException("Not allowed to access/update/remove resource of another company.");
                }
            }
        }
    }

    @Override
    public void onAfterLoad(@NotNull AfterLoadEvent<CompanyAwareDocument> event) {
        if (SecurityUtils.getPrincipal().isPresent()) {
            if (!SecurityUtils.getAuthenticatedPrincipal().getAccountType().isSystemAdministrator()) {
                var companyId = (ObjectId) event.getSource().get("company_id");

                // if company is null we cannot load a document associated with company
                if (companyId == null) {
                    throw new PermissionDeniedException("Missing companyId");
                }

                // verify if companyId from document is the same with the authenticated user
                if (!companyId.toString().equals(SecurityUtils.getAuthenticatedPrincipal().getCompanyId())) {
                    throw new PermissionDeniedException("Not allowed to access/update/remove resource of another company.");
                }
            }
        }
    }
}
