package com.juno.loginApi.api.domain.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    @NotEmpty
    private String id;
    @NotEmpty
    private String pw;
    @NotEmpty
    private String rePw;
    @NotEmpty
    private String email;
}
