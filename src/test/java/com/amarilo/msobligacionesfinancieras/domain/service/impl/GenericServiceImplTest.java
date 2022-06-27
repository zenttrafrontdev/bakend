package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdTypeEntity;
import com.amarilo.msobligacionesfinancieras.domain.service.GenericService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AccountTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.BankRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalOrganizationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.TaxClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.WithholdingTaxGroupRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AccountTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalClassificationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalClassificationTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalOrganizationTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.TaxClassificationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.WithholdingTaxGroupEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class GenericServiceImplTest {

    @Autowired
    GenericService genericService;

    @MockBean
    FinanceThirdTypeRepository financeThirdTypeRepository;
    @MockBean
    FiscalOrganizationTypeRepository fiscalOrganizationTypeRepository;
    @MockBean
    BankRepository bankRepository;
    @MockBean
    AccountTypeRepository accountTypeRepository;
    @MockBean
    WithholdingTaxGroupRepository withholdingTaxGroupRepository;
    @MockBean
    FiscalClassificationRepository fiscalClassificationRepository;
    @MockBean
    FiscalClassificationTypeRepository fiscalClassificationTypeRepository;
    @MockBean
    TaxClassificationRepository taxClassificationRepository;

    @Test
    void findAllFinanceThirdTypes() {
        //given
        var item = new FinanceThirdTypeEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(financeThirdTypeRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllFinanceThirdTypes();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllFiscalOrganizationTypes() {
        //given
        var item = new FiscalOrganizationTypeEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(fiscalOrganizationTypeRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllFiscalOrganizationTypes();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllBanks() {
        //given
        var item = new BankEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(bankRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllBanks();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllAccountTypes() {
        //given
        var item = new AccountTypeEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(accountTypeRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllAccountTypes();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllWithholdingTaxGroups() {
        //given
        var item = new WithholdingTaxGroupEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(withholdingTaxGroupRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllWithholdingTaxGroups();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllFiscalClassifications() {
        //given
        var item = new FiscalClassificationEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(fiscalClassificationRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllFiscalClassifications();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllFiscalClassificationTypes() {
        //given
        var item = new FiscalClassificationTypeEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(fiscalClassificationTypeRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllFiscalClassificationTypes();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }

    @Test
    void findAllTaxClassifications() {
        //given
        var item = new TaxClassificationEntity();
        item.setId(1);
        item.setCode("code");
        item.setName("name");
        var list = List.of(item);
        //when
        when(taxClassificationRepository.findAll()).thenReturn(list);
        var resultList = genericService.findAllTaxClassifications();
        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
        Assertions.assertEquals(item.getCode(), resultList.get(0).getCode());
        Assertions.assertEquals(item.getName(), resultList.get(0).getName());
    }
}