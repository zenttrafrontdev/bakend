package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.domain.financethird.FinanceThirdEntity;
import com.amarilo.msobligacionesfinancieras.domain.financethird.FinanceThirdTypeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class FinanceThirdSpecification {

    private FinanceThirdSpecification() {
    }

    public static Specification<FinanceThirdEntity> hasName(String financeThirdName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(
                        root.get("name"),
                        financeThirdName
                )
        ));
    }

    public static Specification<FinanceThirdEntity> hasIdentification(String identification) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(
                        root.get("identification"),
                        identification
                )
        ));
    }

    public static Specification<FinanceThirdEntity> hasContributorId(String contributorId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(
                        root.get("contributorId"),
                        contributorId
                )
        ));
    }

    public static Specification<FinanceThirdEntity> hasAccountNumber(String accountNumber) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(
                        root.get("accountNumber"),
                        accountNumber
                )
        ));
    }

    public static Specification<FinanceThirdEntity> hasTaxWithholding(String taxWithholding) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(
                        root.get("taxWithholding"),
                        taxWithholding
                )
        ));
    }

    public static Specification<FinanceThirdEntity> hasFinanceThirdTypeId(Integer financeThirdTypeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("financeThirdType");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            financeThirdTypeId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasFiscalOrganizationTypeId(Integer fiscalOrganizationTypeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("fiscalOrganizationType");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            fiscalOrganizationTypeId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasBankId(Integer bankId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("bank");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            bankId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasAccountTypeId(Integer accountTypeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("accountType");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            accountTypeId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasWithholdingTaxGroupId(Integer withholdingTaxGroupId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("withholdingTaxGroup");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            withholdingTaxGroupId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasFiscalClassificationId(Integer fiscalClassificationId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("fiscalClassification");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            fiscalClassificationId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasFiscalClassificationTypeId(Integer fiscalClassificationTypeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("fiscalClassificationType");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            fiscalClassificationTypeId
                    )
            );
        });
    }

    public static Specification<FinanceThirdEntity> hasTaxClassificationId(Integer taxClassificationId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FinanceThirdEntity, FinanceThirdTypeEntity> financeThirdTypeJoin = root.join("taxClassification");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            taxClassificationId
                    )
            );
        });
    }


}
