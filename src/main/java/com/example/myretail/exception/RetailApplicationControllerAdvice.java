package com.example.myretail.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by akrish10 on 5/27/20.
 */

@Slf4j
@ControllerAdvice
public class RetailApplicationControllerAdvice {


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> httpClientErrorExceptionHandler(
            HttpClientErrorException ex) {
        log.error("HttpClientErrorException occurred while calling rest service :", ex);
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(ex.getStatusCode().toString());
        response.setErrorMessage(ex.getResponseBodyAsString());
        return new ResponseEntity<ErrorResponse>(response, ex.getStatusCode());
    }

}
