package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.financethird.FinanceThirdEntity;
import org.springframework.data.jpa.domain.Specification;

public class FinanceThirdSpecification {

    public static Specification<FinanceThirdEntity> hasName(Integer financeThirdName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(
                        root.get("nombre"),
                        financeThirdName
                )
        ));
    }
/*
    public static Specification<FinanceThirdEntity> getSpecificationFromQuery(CollectionCashQuery query) {
        Specification<FinanceThirdEntity> specification = null;
        if (Optional.ofNullable(query.getCustomerId()).isPresent()) {
            specification = buildAndSpecification(null, CollectionCashSpecificationUtils.hasCustomerId(query.getCustomerId()));
        }

        if (Optional.ofNullable(query.getCashReceiptNumber()).isPresent()) {
            specification = buildAndSpecification(specification, CollectionCashSpecificationUtils.hasCashReceiptNumber(query.getCashReceiptNumber()));
        }

        if (Optional.ofNullable(query.getStatus()).isPresent()) {
            specification = buildAndSpecification(specification, CollectionCashSpecificationUtils.hasStatus(query.getStatus()));
        }

        if (Optional.ofNullable(query.getDateFrom()).isPresent() || Optional.ofNullable(query.getDateTo()).isPresent()) {
            specification = buildAndSpecification(specification, CollectionCashSpecificationUtils.isBetweenCreationDates(
                    query.getDateFrom(),
                    query.getDateTo()
            ));
        }

        return specification;
    }

 */
}
