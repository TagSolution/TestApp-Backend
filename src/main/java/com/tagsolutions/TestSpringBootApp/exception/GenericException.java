package com.tagsolutions.TestSpringBootApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class GenericException extends RuntimeException {

    private  final String message;
    private final HttpStatus httpStatus;

}