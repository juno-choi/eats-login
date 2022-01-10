package com.juno.loginApi.api.service.login;

import com.juno.loginApi.api.domain.login.MemberDto;
import com.juno.loginApi.api.domain.login.MemberEntity;
import com.juno.loginApi.api.domain.login.MemberRoles;
import com.juno.loginApi.api.domain.login.MemberVo;
import com.juno.loginApi.api.repo.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;  //encoder 추가

    @Transactional
    @Override
    public MemberVo join(MemberDto md){
        String id = md.getId();
        String pw = md.getPw();
        String rePw = md.getRePw();
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
}
