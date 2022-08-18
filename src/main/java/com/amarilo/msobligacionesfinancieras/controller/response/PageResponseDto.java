package com.amarilo.msobligacionesfinancieras.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponseDto<T> {
    private List<T> content;
    private Integer page;
    private Integer size;
    private Long totalElements;
}
