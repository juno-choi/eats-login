package com.juno.loginApi.api.service.login;

import com.juno.loginApi.api.domain.login.MemberDto;
import com.juno.loginApi.api.exception.login.JoinValidException;
import com.juno.loginApi.api.exception.login.JoinValidExceptionMsg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class LoginServiceImplTest {

    @Autowired
    LoginService loginService;

    @Test
    @DisplayName("rePw 불일치")
    void joinRePwFail(){
        //given
        MemberDto md = new MemberDto();
        md.setId("testId");
        md.setPw("password1");
        md.setRePw("password2");    //불일치
        md.setEmail("mail@mail.com");

        //when
        JoinValidException joinValidException = assertThrows(JoinValidException.class, () -> loginService.join(md));
        //then
        assertEquals(JoinValidExceptionMsg.RE_PW_FAIL.getMsg(),joinValidException.getMsg());
    }

    @Test
    @DisplayName("잘못된 email 형식")
    void joinEmailFail(){
        //given
        MemberDto md = new MemberDto();
        md.setId("testId");
        md.setPw("password1");
        md.setRePw("password1");
        md.setEmail("mail.com");    //형식 오류

        //when
        JoinValidException joinValidException = assertThrows(JoinValidException.class, () -> loginService.join(md));
        //then
        assertEquals(JoinValidExceptionMsg.EMAIL_FAIL.getMsg(),joinValidException.getMsg());
    }
}