package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.TaxClassificationEntity;

public class TaxClassificationFixture {
    public static TaxClassificationEntity getTaxClassificationEntity(){
        var object = new TaxClassificationEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
