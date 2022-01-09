package com.juno.loginApi.api.service.login;

import com.juno.loginApi.api.domain.login.MemberDto;
import com.juno.loginApi.api.domain.login.MemberVo;

public interface LoginService {
    public MemberVo join(MemberDto md);
}
