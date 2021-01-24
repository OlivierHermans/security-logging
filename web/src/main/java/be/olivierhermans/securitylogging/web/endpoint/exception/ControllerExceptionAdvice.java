package be.olivierhermans.securitylogging.web.endpoint.exception;

import be.olivierhermans.securitylogging.domain.model.exception.NoEntityFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(NoEntityFoundException.class)
    public ResponseEntity handleNoEntityFoundException() {
        return ResponseEntity.notFound().build();
    }
}
