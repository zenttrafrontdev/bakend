package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import org.springframework.data.jpa.domain.Specification;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.formatLikeUpperString;

public class FeeSpecification {

    private FeeSpecification() {
    }

    public static Specification<FeeEntity> hasName(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("name")),
                        formatLikeUpperString(name)
                )
        ));
    }

    public static Specification<FeeEntity> hasPeriodicity(String periodicity) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("periodicity")),
                        formatLikeUpperString(periodicity)
                )
        ));
    }

    public static Specification<FeeEntity> hasValueType(String valueType) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("valueType")),
                        formatLikeUpperString(valueType)
                )
        ));
    }

}
