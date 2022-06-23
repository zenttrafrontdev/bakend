package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.springframework.data.jpa.domain.Specification;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.Specificationutils.formatLikeUpperString;

public class ProjectSpecification {

    private ProjectSpecification() {
    }

    public static Specification<ProjectEntity> hasProjectCode(String projectCode) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("projectCode")),
                        formatLikeUpperString(projectCode)
                )
        ));
    }

    public static Specification<ProjectEntity> hasProjectName(String projectName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("projectName")),
                        formatLikeUpperString(projectName)
                )
        ));
    }

    public static Specification<ProjectEntity> hasGroupCode(String groupCode) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("groupCode")),
                        formatLikeUpperString(groupCode)
                )
        ));
    }

    public static Specification<ProjectEntity> hasGroupName(String groupName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("groupName")),
                        formatLikeUpperString(groupName)
                )
        ));
    }

    public static Specification<ProjectEntity> hasConsolidatorCode(String consolidatorCode) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("consolidatorCode")),
                        formatLikeUpperString(consolidatorCode)
                )
        ));
    }

    public static Specification<ProjectEntity> hasConsolidatorName(String consolidatorName) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("consolidatorName")),
                        formatLikeUpperString(consolidatorName)
                )
        ));
    }

    public static Specification<ProjectEntity> hasBuildersCreditBank(String buildersCreditBank) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("buildersCreditBank")),
                        formatLikeUpperString(buildersCreditBank)
                )
        ));
    }

    public static Specification<ProjectEntity> hasPaymentType(String paymentType) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("paymentType")),
                        formatLikeUpperString(paymentType)
                )
        ));
    }

    public static Specification<ProjectEntity> hasStatus(String status) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(
                        criteriaBuilder.upper(root.get("status")),
                        formatLikeUpperString(status)
                )
        ));
    }
}
