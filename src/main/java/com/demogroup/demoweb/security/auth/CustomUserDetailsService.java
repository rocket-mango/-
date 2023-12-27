package com.demogroup.demoweb.security.auth;

import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.exception.AppException;
import com.demogroup.demoweb.exception.ErrorCode;
import com.demogroup.demoweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

//SecurityConfig의 security chain에서 loginProcessingUrl에 설정한 '/login' 으로 post 입력이 오면
//UserDetailsService를 구현한 클래스의 loadUserByUsername 함수가 실행되어 로그인의 유효성을 확인한다.
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<User> all = userRepository.findAll();
        for (User user : all) {
            if(user.getUsername().equals(username)){
                System.out.println("yes");
                return new CustomUserDetails(user);
            }
        }
        return null;
        //throw new AppException(ErrorCode.USERNAME_NOT_FOUND,"가입된 아이디가 없습니다. 다시 입력해주세요.");

    }
}
