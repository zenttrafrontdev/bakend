package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalClassificationTypeEntity;

public class FiscalClassificationTypeFixture {
    public static FiscalClassificationTypeEntity getFiscalClassificationTypeEntity(){
        var object = new FiscalClassificationTypeEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
