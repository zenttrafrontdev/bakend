package com.amarilo.msobligacionesfinancieras.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> onException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, "Acceso Denegado"));
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> onAccessDeniedException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, "Acceso Denegado"));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onIlegalArgumentException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, ex.getMessage()));
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onBusinessException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, ex.getMessage()));
    }

}
