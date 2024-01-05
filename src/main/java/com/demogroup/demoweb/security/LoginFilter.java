package com.demogroup.demoweb.security;

import com.demogroup.demoweb.domain.CustomUserDetails;
import com.demogroup.demoweb.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    //request로부터 아이디와 비밀번호를 받아서
    //usernamepasswordauthenticationtoken에 넣어
    //authenticationmanager에 전달하여 인증을 진행시키는 메소드이다.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("LoginFilter.attemptAuthentication");

        String username = super.obtainUsername(request);
        String password = super.obtainPassword(request);

        System.out.println(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password,null);

        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("successful login");

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String role = ((CustomUserDetails) authentication.getPrincipal())
                .getAuthorities()
                .iterator().next()
                .getAuthority();

        String token = jwtUtils.createToken(customUserDetails, role, 60 * 60 * 1000L);
        response.addHeader("Authorization","Bearer "+token);

//        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//        redirectStrategy.sendRedirect(request, response, "/admin");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("login fail");
        response.setStatus(401);
    }
}
