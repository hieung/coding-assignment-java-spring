package com.gfg.product.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleAllUncaughtException(
            RuntimeException ex) {
        String message = "Internal Server Error";
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, message, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(
            ProductNotFoundException ex, WebRequest request) {
        String message = "Product not found";
        ApiError apiError = new ApiError(NOT_FOUND, message, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(
            NoDataFoundException ex, WebRequest request) {
        String message = "Data is empty";
        ApiError apiError = new ApiError(NOT_FOUND, message, ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}