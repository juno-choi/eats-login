package com.juno.loginApi.api.repo.login;

import com.juno.loginApi.api.domain.login.MemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LoginRepository {

    @PersistenceContext
    private EntityManager em;

    public MemberEntity save(MemberEntity member){
        em.persist(member);
        return member;
    }
}
