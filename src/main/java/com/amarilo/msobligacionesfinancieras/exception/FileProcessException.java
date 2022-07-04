package com.amarilo.msobligacionesfinancieras.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class FileProcessException extends RuntimeException {
    private final List<FileProcessErrorDto> errorList;

    public FileProcessException(String message, List<FileProcessErrorDto> errorList) {
        super(message);
        this.errorList = errorList;
    }
}
