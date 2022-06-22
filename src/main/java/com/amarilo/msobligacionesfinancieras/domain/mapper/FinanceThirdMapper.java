package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.FinanceThirdDto;
import com.amarilo.msobligacionesfinancieras.domain.financethird.FinanceThirdEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FinanceThirdMapper {
    FinanceThirdMapper INSTANCE = Mappers.getMapper(FinanceThirdMapper.class);

    List<FinanceThirdDto> financeThirdListToFinanceThirdDtoList(List<FinanceThirdEntity> financeThirdList);

    FinanceThirdEntity financeThirdDtoToFinanceThird(FinanceThirdDto financeThirdDto);

    FinanceThirdDto financeThirdEntityToFinanceThirdDto(FinanceThirdEntity financeThirdEntity);
}
