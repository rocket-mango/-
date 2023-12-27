package com.demogroup.demoweb.service;

import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.domain.dto.UserDTO;
import com.demogroup.demoweb.exception.AppException;
import com.demogroup.demoweb.exception.ErrorCode;
import com.demogroup.demoweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String secretKey;
    @Value("${jwt.token.expire-time}")
    private Long expireTime;

    //회원가입하는 메서드
    public User join(UserDTO dto){
        String username = dto.getUsername();
        if (isUsernameDuplicated(username)){
            throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 가입된 아이디가 존재합니다.");
        }
        //관리자 또는 해커가 사용자들의 비밀번호를 알 수 없도록 encoder를 사용해 암호화
        String encodedPw = encoder.encode(dto.getPassword());
        User user = User.toEntity(dto,encodedPw);
        userRepository.save(user);
        System.out.println("UserService.join");

        return user;
    }

    //아이디 중복여부 메서드
    public Boolean isUsernameDuplicated(String username){
        Collection<User> all = userRepository.findAll();
        for (User user : all) {
            if(username.equals(user.getUsername())){
                //아이디가 중복되어 회원가입 불가능
                return true;
            }
        }
        //중복 안됨, 회원가입 가능
        return false;
    }





}
