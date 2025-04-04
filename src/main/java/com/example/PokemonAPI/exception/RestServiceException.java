package com.example.PokemonAPI.exception;

import org.springframework.http.HttpStatus;
import java.io.Serial;

public class RestServiceException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;

    public RestServiceException(String message, HttpStatus
            httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
