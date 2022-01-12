package com.juno.loginApi.api.controller.login;

import com.juno.loginApi.api.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LoginController {

    private final LoginService loginService;

}
