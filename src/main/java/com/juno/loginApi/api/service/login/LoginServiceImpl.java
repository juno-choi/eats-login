package com.juno.loginApi.api.service.login;

import com.juno.loginApi.api.domain.login.MemberDto;
import com.juno.loginApi.api.domain.login.MemberEntity;
import com.juno.loginApi.api.domain.login.MemberRoles;
import com.juno.loginApi.api.domain.login.MemberVo;
import com.juno.loginApi.api.repo.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final LoginRepository loginRepository;

    @Transactional
    @Override
    public MemberVo join(MemberDto md){
        String id = md.getId();
        String pw = md.getPw();
        String rePw = md.getRePw();
        String email = md.getEmail();

        MemberEntity me = MemberEntity.builder().id(id).pw(pw).email(email).auth(MemberRoles.MEMBER.getRole()).build();

        MemberEntity save = loginRepository.save(me);

        return MemberVo.builder().id(save.getId()).build();
    }
}
