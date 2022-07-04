package com.amarilo.msobligacionesfinancieras.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileProcessErrorDto {
    private long lineNumber;
    private String line;
    private String field;
    private String message;
}
