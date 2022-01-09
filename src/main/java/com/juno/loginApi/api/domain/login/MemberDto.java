package com.juno.loginApi.api.domain.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private String id;
    private String pw;
    private String rePw;
    private String email;
}
