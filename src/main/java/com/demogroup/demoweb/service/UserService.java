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
    public void join(UserDTO dto) {
        System.out.println("UserService.join");

        //유저 아이디 중복확인
        User isDuplicated = userRepository.findByUsername(dto.getUsername());

        if(isDuplicated==null){
            String encodedPw = encoder.encode(dto.getPassword());
            User user = User.toEntity(dto, encodedPw);
            userRepository.save(user);
        }else{
            throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 가입된 아이디가 존재합니다.");
        }

    }

    //회원가입하는 메서드
//    public UserDTO join(UserDTO dto){
//        String username = dto.getUsername();
//        if (isUsernameDuplicated(username)){
//            throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 가입된 아이디가 존재합니다.");
//        }
//        //관리자 또는 해커가 사용자들의 비밀번호를 알 수 없도록 encoder를 사용해 암호화
//        String encodedPw = encoder.encode(dto.getPassword());
//        User user = User.toEntity(dto,encodedPw);
//        userRepository.save(user);
//
//        return dto;
//    }
//
//    //아이디 중복여부 메서드
//    public Boolean isUsernameDuplicated(String username){
//        Collection<User> all = userRepository.findAll();
//        for (User user : all) {
//            if(username.equals(user.getUsername())){
//                //아이디가 중복되어 회원가입 불가능
//                return true;
//            }
//        }
//        //중복 안됨, 회원가입 가능
//        return false;
//    }
//
//    //username 변경
//    public UserDTO modify(UserDTO dto) {
//        if(isUsernameDuplicated(dto.getUsername())){
//            throw new AppException(ErrorCode.USERNAME_DUPLICATED,"이미 가입된 아이디가 존재합니다.");
//        }
//        else{
//            User findUser = userRepository.findByUserId(dto.getUid());
//            findUser.updateUsername(dto.getUsername());
//            userRepository.save(findUser);
//            return UserDTO.toDTO(findUser,dto.getPassword());
//        }
//    }
}
