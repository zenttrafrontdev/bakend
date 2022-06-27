package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.formatLikeUpperString;

public class FeeItemSpecification {

    private FeeItemSpecification() {
    }

    public static Specification<FeeItemEntity> hasFeeId(Integer feeId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<FeeItemEntity, FeeEntity> financeThirdTypeJoin = root.join("fee");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            financeThirdTypeJoin.get("id"),
                            feeId
                    )
            );
        });
    }

    public static Specification<FeeItemEntity> hasValue(String value) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("value")),
                        formatLikeUpperString(value)
                )
        ));
    }

    public static Specification<FeeItemEntity> hasStartDateGreaterThan(LocalDate startDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate)
        ));
    }

    public static Specification<FeeItemEntity> hasEndDateLessThan(LocalDate endDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate)
        ));
    }
}
