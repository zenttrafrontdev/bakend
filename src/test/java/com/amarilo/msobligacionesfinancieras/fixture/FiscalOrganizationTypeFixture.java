package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalOrganizationTypeEntity;

public class FiscalOrganizationTypeFixture {
    public static FiscalOrganizationTypeEntity getFiscalOrganizationTypeEntity(){
        var object = new FiscalOrganizationTypeEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
