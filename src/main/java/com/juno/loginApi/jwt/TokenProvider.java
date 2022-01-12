package com.juno.loginApi.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    private final String secretKey;   //@Value를 통해 yml에 저장된 secret 값을 가져옴, Lombok valid 아님!
    private final Long tokenValidityInMilliseconds; //토큰이 살아있는 시간 (1000L * 60 * 60 = 1시간)

    private Key key;    //afterPropertiesSet()에서 key값을 할당해줌
    private final String AUTH = "auth";

    //생성자에서 secretKey값을 base64로 인코딩하여 값을 넣어주고 토큰의 생명주기도 설정한다.
    public TokenProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.token-validity-in-seconds}") Long tokenValidityInMilliseconds) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    //bean이 생성되고 의존성 주입이 완료된 다음 init()을 실행하여 base64로 인코딩된 키 값을 decoding하여 key 변수에 할당
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //Authentication 객체의 권한정보를 이용해서 토큰을 생성하는 createToken 메서드
    public String createToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()   //authentication 정보를 가져옴
                .map(GrantedAuthority::getAuthority)                    //GrantedAuthority 객체로 변환 후 getAuthority()
                .collect(Collectors.joining(","));              //배열을 ,로 연결하여서 하나의 String으로 만듦

        Date now = new Date();
        long nowTime = now.getTime();  //현재 시간
        Date validity = new Date(nowTime + this.tokenValidityInMilliseconds);  //현재 시간 + 설정한 토큰 주기를 더해줌

        return Jwts.builder()                               //토큰 생성후 리턴
                .setSubject(authentication.getName())       //토큰 제목
                .setIssuedAt(now)                           //토큰 발행 일자
                .claim(AUTH, authorities)            //담고 싶은 데이터 (key, value) = payload에 들어가는 정보, 여기서는 권한들에 대한 정보를 넣어줌
                .signWith(key, SignatureAlgorithm.HS512)    //key값과 함께 우리가 사용할 암호화 알고리즘 선언
                .setExpiration(validity)                    //토큰 주기 설정
                .compact();
    }

    //token을 파라미터로 받아서 토큰의 정보를 읽어온 후 정보를 확인하여 권한을 반환하는 메서드
    public Authentication getAuthentication(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)     //서명한 key 값
                .build()
                .parseClaimsJws(token)  //전달 받은 token
                .getBody();             //parse하여 전달된 값

        Collection<GrantedAuthority> authorities = Arrays.stream(claims.get(AUTH).toString().split(","))    //파싱하여 가져온 claims에서 auth key 값으로 권한들을 가져와서 배열로 만듦
                .map(SimpleGrantedAuthority::new)   //배열을 스트림으로 변환 후 SimpleGrantedAuthority 객체로 변환
                .collect(Collectors.toList());      //List로 만듦

        User principal = new User(claims.getSubject(), "", authorities);    //token에 저장된 제목으로 User 객체 생성

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);  //인터페이스 Authentication의 구현체를 통해 Authentication객체 반환
    }

    //token의 유효성 검사, 각 Exception에 따라 처리 해주면 됨!
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);  //토큰을 파싱했을 때 정상이 아니라면 exception 발생
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}