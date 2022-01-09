package com.juno.loginApi.api.controller.login;

import com.juno.loginApi.api.domain.common.CommonV1;
import com.juno.loginApi.api.domain.login.MemberDto;
import com.juno.loginApi.api.domain.login.MemberVo;
import com.juno.loginApi.api.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/join")
    public ResponseEntity join(@RequestBody MemberDto md){
        MemberVo join = loginService.join(md);

        CommonV1 result = CommonV1.builder()
                .code("200")
                .result("success")
                .msg("회원가입 성공")
                .data(join)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
