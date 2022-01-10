package com.juno.loginApi.api.domain.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
public class MemberEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Long idx;

    @Column(nullable = false, unique = true, length = 20)
    private String id;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, length = 200)
    private String pw;

    @Column(name = "auth")
    @CollectionTable(name = "member_roles")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> auth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.auth.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
