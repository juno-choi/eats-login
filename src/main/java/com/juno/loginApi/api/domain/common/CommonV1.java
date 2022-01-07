package com.juno.loginApi.api.domain.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class CommonV1<T> {
    private String result;  //success, fail 구분
    private String code;    //api 고유 상태코드
    private String msg;     //반환 메세지
    private T data;         //반환 데이터

    @Builder
    public CommonV1(String result, String code, String msg, T data) {
        Assert.hasText(result, "result 값이 비어있습니다.");
        Assert.hasText(code, "code 값이 비어있습니다.");
        Assert.notNull(data, "data 값이 비어있습니다.");

        this.result = result;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
