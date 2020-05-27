package com.example.myretail.exception;

/**
 * Created by akrish10 on 5/27/20.
 */
public class RetailApplicationException extends RuntimeException {

    private String errorMessage;
    private int responseStatusCode;

    /**
     * AuthoringApplicationException .
     *
     * @param errorMessage       .
     * @param responseStatusCode .
     */
    public RetailApplicationException(
            String errorMessage, int responseStatusCode) {
        super("RetailApplicationException Occurred");
        this.errorMessage = errorMessage;
        this.responseStatusCode = responseStatusCode;
    }




}
