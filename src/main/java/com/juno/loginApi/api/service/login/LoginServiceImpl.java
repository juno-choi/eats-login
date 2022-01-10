package com.juno.loginApi.api.service.login;

import com.juno.loginApi.api.domain.login.MemberDto;
import com.juno.loginApi.api.domain.login.MemberEntity;
import com.juno.loginApi.api.domain.login.MemberRoles;
import com.juno.loginApi.api.domain.login.MemberVo;
import com.juno.loginApi.api.exception.login.JoinValidException;
import com.juno.loginApi.api.exception.login.JoinValidExceptionMsg;
import com.juno.loginApi.api.repo.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;  //encoder 추가

    @Transactional
    @Override
    public MemberVo join(MemberDto md){
        //join에 대한 유효성 검사 method
        joinValidation(md);

        String id = md.getId();
        String pw = md.getPw();
        String email = md.getEmail();

        Set<String> roles = new HashSet<>();
        roles.add(MemberRoles.MEMBER.getRole());

        MemberEntity me = MemberEntity.builder()
                .id(id)
                .pw(passwordEncoder.encode(pw)) //비밀번호 암호화
                .email(email)
                .auth(roles)
                .build();

        MemberEntity save = loginRepository.save(me);

        return MemberVo.builder().id(save.getId()).build();
    }
    //그 외 유효성 검사
    private void joinValidation(MemberDto md){
        //pw와 rePw이 같은 값인지 체크
        if(!md.getPw().equals(md.getRePw())) throw new JoinValidException(JoinValidExceptionMsg.RE_PW_FAIL.getMsg(), HttpStatus.BAD_REQUEST);
        //email 형식 체크
        if(!md.getEmail().contains("@")) throw new JoinValidException(JoinValidExceptionMsg.EMAIL_FAIL.getMsg(), HttpStatus.BAD_REQUEST);
    }
}
