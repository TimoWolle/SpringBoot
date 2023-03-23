package training.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(RuntimeException rex) {
        return new ResponseEntity<>(ErrorResponse.create(rex, HttpStatus.INTERNAL_SERVER_ERROR, rex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> entityNotFoundException(RuntimeException exception, WebRequest request) {
        String msg = getMessageSource().getMessage("exception.id_not_found", new Object[] {exception.getLocalizedMessage()}, LocaleContextHolder.getLocale());
        return handleExceptionInternal(exception, msg, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
