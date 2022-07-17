package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;

import java.time.LocalDate;
import java.time.Period;

public class QuotaFixture {

    public static QuotaEntity getQuotaEntity(){
        return QuotaEntity.builder()
                .id(1)
                .approvedQuota("120000000000000")
                .availableQuota("120000000000000")
                .quotaUsed("0000000")
                .builderAvailableQuota("0000000")
                .approvedQuotaDate(LocalDate.now())
                .expirationQuotaDate(LocalDate.now().plus(Period.ofDays(15)))
                .build();
    }

    public static QuotaDto getQuotaDto(){
        return QuotaDto.builder()
                .id(1)
                .approvedQuota("120000000000000")
                .availableQuota("120000000000000")
                .quotaUsed("0000000")
                .builderAvailableQuota("0000000")
                .approvedQuotaDate(LocalDate.now())
                .expirationQuotaDate(LocalDate.now().plus(Period.ofDays(15)))
                .build();
    }
}
