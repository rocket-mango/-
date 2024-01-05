package com.demogroup.demoweb.utils;


import com.demogroup.demoweb.domain.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

//UsernamePasswordAuthenticationFilter를 상속받는 LoginFilter와는 다르게
//이 클래스는 javadoc에서 관리시켜주기 위해 @Component 어노테이션을 붙여준다.
@Component
public class JWTUtils {

    private Key key;

    public JWTUtils(@Value("${spring.jwt.secret}")String secretKey){
        //secretkey를 암호화한다.
        byte[] decodedKey = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(decodedKey);
    }

    public String getName(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("name",String.class);
    }

    public String getUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("username",String.class);
    }

    public String getNickname(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("nickname",String.class);
    }

    public String getEmail(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("email",String.class);
    }

    public String getRole(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .get("role",String.class);
    }

    public String createToken(CustomUserDetails dto, String role, Long expiredMs){

        Claims claims= Jwts.claims();
        claims.put("name",dto.getName());
        claims.put("username",dto.getUsername());
        claims.put("nickname",dto.getNickname());
        claims.put("email",dto.getEmail());
        claims.put("role",role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
