package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalClassificationEntity;

public class FiscalClassificationFixture {
    public static FiscalClassificationEntity getFiscalClassificationEntity(){
        var object = new FiscalClassificationEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
