package com.github.angel.raa.excpetion;

import com.github.angel.raa.utils.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *  Global exception handler for the application.
 *  Handles ResourceNotFoundException and InvalidPasswordException.
 *  Provides a standardized response structure for errors.
 */
@RestControllerAdvice
public class GlobalDefaultException {
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<Response<Object>> handleResourceNotFoundException(@NotNull ResourceException ex) {
        ZoneId zoneId = ZoneId.systemDefault();
        Response<Object> response = Response.builder().message(ex.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .error(true)
                .timestamp(LocalDateTime.now(zoneId))
                .data(null)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-Type", "ResourceNotFound");
        return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Response<Object>> handleInvalidPasswordException(@NotNull InvalidPasswordException ex) {
        Response<Object> response = Response.builder().message(ex.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .error(true)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-Type", "InvalidPassword");
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Response<Object>> handleHttpRequestMethodNotSupportedException(@NotNull HttpRequestMethodNotSupportedException ex) {
        Response<Object> response = Response.builder().message(ex.getMessage())
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .error(true)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-Type", "HttpRequestMethodNotSupported");
        return new ResponseEntity<>(response, headers,HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Response<Object>> handleHttpRequestMethodNotSupportedException(@NotNull Exception ex) {
        Response<Object> response = Response.builder()
                .message("Invalid media type")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(true)
                .timestamp(LocalDateTime.now())
                .data(null)


                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Error-Type", "HttpRequestMethodNotSupported");
        return new  ResponseEntity<>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
