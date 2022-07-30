package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class SpecificationUtils {

    private SpecificationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String LIKE_STRING_QUERY = "%%%s%%";

    public static <T> Specification<T> buildAndSpecification(@Nullable Specification<T> specification, Specification<T> addSpecification) {
        return !Optional.ofNullable(specification).isPresent() ? Specification.where(addSpecification) : specification.and(addSpecification);
    }

    public static <T> Specification<T> buildOrSpecification(@Nullable Specification<T> specification, Specification<T> addSpecification) {
        return !Optional.ofNullable(specification).isPresent() ? Specification.where(addSpecification) : specification.or(addSpecification);
    }

    public static String formatLikeUpperString(String query) {
        return query == null ? null : String.format(LIKE_STRING_QUERY, query.toUpperCase());
    }
}
