package com.juno.loginApi.api.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonError {
    private String msg;
    private String detail = "";

    public CommonError(String msg) {
        this.msg = msg;
    }
}
