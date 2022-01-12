package com.juno.loginApi.api.exception.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JoinValidException extends RuntimeException {
    private String msg;
    private HttpStatus status;
    private List<ObjectError> allErrors = new LinkedList<>();

    public JoinValidException(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }
}
