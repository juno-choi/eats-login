package com.juno.loginApi.api.exception.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JoinValidExceptionMsg {
    RE_PW_FAIL("두 비밀번호가 일치하지 않습니다."),
    EMAIL_FAIL("이메일 형식을 확인해주세요.")
    ;
    private String msg;
}
