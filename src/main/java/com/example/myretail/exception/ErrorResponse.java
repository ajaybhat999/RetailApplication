package com.example.myretail.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by akrish10 on 5/27/20.
 */
@Getter
@Setter
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
}
