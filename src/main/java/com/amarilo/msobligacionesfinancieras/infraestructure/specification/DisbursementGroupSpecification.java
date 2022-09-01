package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementGroupEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;

public class DisbursementGroupSpecification {

    private DisbursementGroupSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<DisbursementGroupEntity> hasConsecutive(Integer consecutive) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("consecutive"), consecutive)
        ));
    }

    public static Specification<DisbursementGroupEntity> hasStartDateGreaterThan(LocalDate startDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), startDate)
        ));
    }

    public static Specification<DisbursementGroupEntity> hasEndDateLessThan(LocalDate endDate) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDate)
        ));
    }

    public static Specification<DisbursementGroupEntity> hasProjectId(Integer projectId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<DisbursementGroupEntity, ProjectEntity> disbursementGroupEntityProjectEntityJoin = root.join("project");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(
                            disbursementGroupEntityProjectEntityJoin.get("id"),
                            projectId
                    )
            );
        });
    }

    public static Specification<DisbursementGroupEntity> hasObligationNumber(String obligationNumber) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("obligationNumber"), obligationNumber)
        ));
    }

    public static Specification<DisbursementGroupEntity> hasOracleId(String oracleId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("oracleId"), oracleId)
        ));
    }
}
