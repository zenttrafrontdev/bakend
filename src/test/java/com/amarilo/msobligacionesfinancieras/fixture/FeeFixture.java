package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;

public class FeeFixture {
    public static FeeEntity getFeeEntity(){
        return FeeEntity.builder()
                .name("UVR")
                .periodicity("1")
                .valueType("1")
                .build();
    }

    public static FeeDto getFeeDto(){
        return FeeDto.builder()
                .id(1)
                .name("UVR")
                .periodicity(1)
                .valueType(1)
                .build();
    }

    public static FeeDto getFeeDtoWithId(Integer id){
        return FeeDto.builder()
                .id(id)
                .name("UVR")
                .periodicity(1)
                .valueType(1)
                .build();
    }

    public static FeeDto getFeeDtoWithValueType(Integer valueType){
        return FeeDto.builder()
                .id(1)
                .name("UVR")
                .periodicity(1)
                .valueType(valueType)
                .build();
    }
}
