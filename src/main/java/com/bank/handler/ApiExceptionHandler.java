package com.bank.handler;

import com.bank.exception.JwtAuthenticationException;
import com.bank.exception.NotBalanceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleNotAuthorizedException(JwtAuthenticationException ex) {
        log.info("ExceptionHandler for NotAuthorizedException");

        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        apiError.setTimestamp(LocalDateTime.now());

        return apiError;
    }

    @ExceptionHandler(NotBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotBalanceException(NotBalanceException ex) {
        log.info("ExceptionHandler for NotBalanceException");

        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        apiError.setTimestamp(LocalDateTime.now());

        return apiError;
    }
}
