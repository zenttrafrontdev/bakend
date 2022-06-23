package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdTypeEntity;

public class FinanceThirdTypeFixture {
    public static FinanceThirdTypeEntity getFinanceThirdTypeEntity(){
        var object = new FinanceThirdTypeEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
