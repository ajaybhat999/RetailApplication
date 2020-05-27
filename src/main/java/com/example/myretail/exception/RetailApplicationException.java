package com.example.myretail.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by akrish10 on 5/27/20.
 */
@Getter
@Setter
public class RetailApplicationException extends RuntimeException {

    private String errorMessage;
    private String responseStatusCode;
    private HttpStatus httpStatus;

    /**
     * AuthoringApplicationException .
     *
     * @param errorMessage       .
     * @param responseStatusCode .
     */
    public RetailApplicationException(
            String errorMessage, String responseStatusCode, HttpStatus httpStatus) {
        super("RetailApplicationException Occurred");
        this.errorMessage = errorMessage;
        this.responseStatusCode = responseStatusCode;
        this.httpStatus = httpStatus;
    }




}
