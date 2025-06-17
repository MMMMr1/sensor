package com.mikhalenok.monitor.sensors.infrastructure.exception.handler;

import com.mikhalenok.monitor.sensors.infrastructure.exception.NotFoundException;
import com.mikhalenok.monitor.sensors.infrastructure.exception.UserAlreadyExistsException;
import com.mikhalenok.monitor.sensors.presentation.model.error.ExceptionErrorRs;
import com.mikhalenok.monitor.sensors.presentation.model.error.ExceptionListRs;
import com.mikhalenok.monitor.sensors.presentation.model.error.ExceptionStructuredRs;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionListRs onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ExceptionStructuredRs> error = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ExceptionStructuredRs(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ExceptionListRs(error);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionErrorRs> onConstraintViolationException(
            ConstraintViolationException e) {
        return  List.of(new ExceptionErrorRs(e.getMessage()));
    }
    @ExceptionHandler( ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionErrorRs handleValidationException(ValidationException exception) {
        return new ExceptionErrorRs(exception.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionErrorRs handleNotFoundException(NotFoundException exception) {
        return new ExceptionErrorRs(exception.getMessage());
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionErrorRs handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ExceptionErrorRs(exception.getMessage());
    }
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionErrorRs handleDuplicateKeyException(DuplicateKeyException exception) {
        return new ExceptionErrorRs(exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionErrorRs handleAccessDeniedException(AccessDeniedException e) {
        return new ExceptionErrorRs(e.getMessage());
    }
    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionErrorRs handleUserAlreadyExistsException(LoginException exception) {
        return new ExceptionErrorRs(exception.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ExceptionErrorRs handleException(Exception exception) {
        return new ExceptionErrorRs(exception.getMessage());
    }
}
