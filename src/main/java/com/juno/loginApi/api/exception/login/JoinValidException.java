package com.juno.loginApi.api.exception.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.Queue;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JoinValidException extends RuntimeException {
    private String msg;
    private HttpStatus status;
    private Queue<ObjectError> allErrors;

}
