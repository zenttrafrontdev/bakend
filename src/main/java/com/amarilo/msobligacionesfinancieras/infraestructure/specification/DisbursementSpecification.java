package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.DisbursementOperationTypeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

public class DisbursementSpecification {

    private DisbursementSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<DisbursementEntity> hasConsecutive(Integer consecutive) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("consecutive"), consecutive)
        ));
    }

    public static Specification<DisbursementEntity> hasDate(LocalDate date) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("date"), date)
        ));
    }

    public static Specification<DisbursementEntity> hasOperationTypeId(Integer operationTypeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DisbursementEntity, DisbursementOperationTypeEntity> disbursementEntityDisbursementOperationTypeEntityJoin = root.join("disbursementOperationType");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            disbursementEntityDisbursementOperationTypeEntityJoin.get("id"),
                            operationTypeId
                    )
            );
        });
    }

    public static Specification<DisbursementEntity> hasProjectSourceId(Integer projectSourceId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DisbursementEntity, ProjectEntity> disbursementEntityProjectEntityJoin = root.join("project");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            disbursementEntityProjectEntityJoin.get("id"),
                            projectSourceId
                    )
            );
        });
    }

    public static Specification<DisbursementEntity> hasFinanceThirdId(Integer financeThirdId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DisbursementEntity, FinanceThirdEntity> disbursementEntityFinanceThirdEntityJoin = root.join("financeThird");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            disbursementEntityFinanceThirdEntityJoin.get("id"),
                            financeThirdId
                    )
            );
        });
    }

    public static Specification<DisbursementEntity> hasProviderId(Integer providerId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DisbursementEntity, FinanceThirdEntity> disbursementEntityFinanceThirdEntityJoin = root.join("provider");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            disbursementEntityFinanceThirdEntityJoin.get("id"),
                            providerId
                    )
            );
        });
    }

    public static Specification<DisbursementEntity> hasPaymentProviderId(Integer paymentProviderId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DisbursementEntity, FinanceThirdEntity> disbursementEntityFinanceThirdEntityJoin = root.join("paymentProvider");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            disbursementEntityFinanceThirdEntityJoin.get("id"),
                            paymentProviderId
                    )
            );
        });
    }

    public static Specification<DisbursementEntity> hasDisbursementInvoiceNumber(String disbursementInvoiceNumber) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("disbursementInvoiceNumber"), disbursementInvoiceNumber)
        ));
    }
}
