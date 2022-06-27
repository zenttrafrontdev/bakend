package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;

public class PageRequestFixture {
    public static <T> PageRequestDto<T> getPageRequestDto(Object object){
        return PageRequestDto.<T>builder()
                .query((T) object)
                .page(0)
                .size(10)
                .build();
    }
}
