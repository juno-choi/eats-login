package com.juno.loginApi.api.domain.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRoles {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_USER")
    ;
    private String role;
}
