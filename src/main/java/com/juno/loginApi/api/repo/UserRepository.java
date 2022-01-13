package com.juno.loginApi.api.repo;

import com.juno.loginApi.api.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "authorities")    //Eager 조회로 권한 정보를 같이 조회
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);   //user정보와 권한 정보를 한번에 가져오는 메서드
}
