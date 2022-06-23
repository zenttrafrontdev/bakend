package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;

public class BankFixture {
    public static BankEntity getBankEntity(){
        var object = new BankEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
