package com.amarilo.msobligacionesfinancieras.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> onException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, "Ha ocurrido un error en el sistema, por favor comunicarse con el administrador"));
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> onAccessDeniedException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, "Acceso Denegado"));
    }

    @ExceptionHandler({IllegalArgumentException.class, BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onIlegalArgumentException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, ex.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            builder.append(violation.getMessage().concat(". "));
        }
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ErrorDto(1, builder.toString()));
    }

}
