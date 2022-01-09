package com.juno.loginApi.api.domain.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberVo {
    String id;
    String pw;
    String email;
}
