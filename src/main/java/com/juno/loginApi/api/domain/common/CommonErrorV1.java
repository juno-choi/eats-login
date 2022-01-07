package com.juno.loginApi.api.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonErrorV1<T> {
    private T error;
}
