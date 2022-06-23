package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.WithholdingTaxGroupEntity;

public class WithholdingTaxGroupFixture {
    public static WithholdingTaxGroupEntity getWithholdingTaxGroupEntity(){
        var object = new WithholdingTaxGroupEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
