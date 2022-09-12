package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementGroupEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.PaymentEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

public class PaymentSpecification {

    private PaymentSpecification() {
    }

    public static Specification<PaymentEntity> hasStartDateGreaterThan(LocalDate startDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), startDate)
        ));
    }

    public static Specification<PaymentEntity> hasEndDateLessThan(LocalDate endDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDate)
        ));
    }

    public static Specification<PaymentEntity> hasDisbursementGroupByObligationNumber(Integer disbursementGroupId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<PaymentEntity, DisbursementGroupEntity> paymentEntityDisbursementGroupEntityJoin = root.join("disbursementGroup");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            paymentEntityDisbursementGroupEntityJoin.get("id"),
                            disbursementGroupId
                    )
            );
        });
    }

}
