package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;

import java.time.LocalDate;

import static com.amarilo.msobligacionesfinancieras.fixture.FeeFixture.getFeeEntity;

public class FeeItemFixture {

    public static FeeItemEntity getFeeItemEntity(){
        return FeeItemEntity.builder()
                .id(1)
                .value("120000000")
                .startDate(LocalDate.of(2022, 01, 01))
                .endDate(LocalDate.of(2022, 01, 31))
                .fee(getFeeEntity())
                .build();
    }
}
