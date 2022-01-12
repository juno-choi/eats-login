package com.juno.loginApi.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider provider;

    //jwt 토큰의 인증 정보를 현재 실행중인 security context에 저장하는 역할을 수행
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);  //request로부터 header에서 토큰 정보를 가져옴
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && provider.validateToken(jwt)) {  //TokenProvider에 작성한 토큰 유효성검사 실행
            Authentication authentication = provider.getAuthentication(jwt);    //토큰에서 Authentication 객체를 반환 받고
            SecurityContextHolder.getContext().setAuthentication(authentication);   //security context에 저장
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI); //유효성 검사 실패
        }

        chain.doFilter(request, response);
    }

    //reuqest의 header에서 토큰의 정보를 꺼내오기 위한 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
