package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BusinessAreaEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.QuotaClassificationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.QuotaTypeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

public class QuotaSpecification {

    private QuotaSpecification() {
    }

    public static Specification<QuotaEntity> hasQuotaTypeId(Integer quotaTypeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<QuotaEntity, QuotaTypeEntity> quotaTypeEntityJoin = root.join("quotaType");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            quotaTypeEntityJoin.get("id"),
                            quotaTypeId
                    )
            );
        });
    }

    public static Specification<QuotaEntity> hasQuotaClassificationId(Integer quotaClassificationId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<QuotaEntity, QuotaClassificationEntity> quotaClassificationEntityJoin = root.join("quotaClassification");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            quotaClassificationEntityJoin.get("id"),
                            quotaClassificationId
                    )
            );
        });
    }

    public static Specification<QuotaEntity> hasBusinessAreaId(Integer businessAreaId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<QuotaEntity, BusinessAreaEntity> businessAreaEntityJoin = root.join("businessArea");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            businessAreaEntityJoin.get("id"),
                            businessAreaId
                    )
            );
        });
    }

    public static Specification<QuotaEntity> hasProjectId(Integer projectId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<QuotaEntity, ProjectEntity> projectEntityJoin = root.join("project");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            projectEntityJoin.get("id"),
                            projectId
                    )
            );
        });
    }

    public static Specification<QuotaEntity> hasBankId(Integer bankId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<QuotaEntity, BankEntity> bankEntityJoin = root.join("bank");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            bankEntityJoin.get("id"),
                            bankId
                    )
            );
        });
    }

    public static Specification<QuotaEntity> hasApprovedQuotaDate(LocalDate approvedQuotaDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("approvedQuotaDate"), approvedQuotaDate)
        ));
    }

    public static Specification<QuotaEntity> hasExpirationQuotaDate(LocalDate expirationQuotaDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("expirationQuotaDate"), expirationQuotaDate)
        ));
    }

}
