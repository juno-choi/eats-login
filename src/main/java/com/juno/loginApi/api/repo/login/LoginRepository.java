package com.juno.loginApi.api.repo.login;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LoginRepository {

    @PersistenceContext
    private EntityManager em;

}
