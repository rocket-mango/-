package com.demogroup.demoweb.security.auth;

import com.demogroup.demoweb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//userDetails란 spring security에서 사용자의 정보를 담는 인터페이스이다.
//로그인한 사용자를 userdetail이라는 객체로 따로 만들어 저장한다.
@AllArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {
    private User user;

    //사용자가 가진 권한 모음
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections=new ArrayList<>();
        collections.add(()->user.getRole().getRoleName());
        return null;
    }

    //로그인한 사용자의 패스워드를 가져온다.
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //로그인한 사용자의 username을 가져온다. (로그인 id)
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겼는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정의 password 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //사용가능한 계정인지
    @Override
    public boolean isEnabled() {
        return true;
    }
}
