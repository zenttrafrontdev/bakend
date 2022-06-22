package com.amarilo.msobligacionesfinancieras.infraestructure.specification;

import org.springframework.data.jpa.domain.Specification;

public interface Specificationutils<T> {

    default Specification<T> buildAndSpecification(Specification<T> firstSpecification, Specification<T> secondSpecification){
        if(firstSpecification == null){
            return secondSpecification;
        }
        return firstSpecification.and(secondSpecification);
    }
}
