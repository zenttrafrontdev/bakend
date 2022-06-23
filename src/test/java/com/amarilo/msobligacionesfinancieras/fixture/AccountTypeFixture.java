package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AccountTypeEntity;

public class AccountTypeFixture {
    public static AccountTypeEntity getAccountTypeEntity(){
        var object = new AccountTypeEntity();
        object.setId(1);
        object.setCode("code");
        object.setName("name");
        return object;
    }
}
