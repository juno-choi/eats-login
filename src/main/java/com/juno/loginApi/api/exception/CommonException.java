package com.juno.loginApi.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException{
    private String msg;
    private HttpStatus status;
}
