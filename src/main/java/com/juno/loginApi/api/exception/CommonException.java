package com.juno.loginApi.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommonException extends RuntimeException{
    private String msg;
    private HttpStatus status;
}
