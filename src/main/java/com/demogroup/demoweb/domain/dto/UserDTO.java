package com.demogroup.demoweb.domain.dto;

import com.demogroup.demoweb.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long uid;
    private String name;
    private String nickname;
    private String username;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=.*\\S+$).{8,16}",message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    private String email;

    //'USER'
    private String role;
//    private LocalDateTime createDate;
//    private LocalDateTime modifiedDate;

    public static UserDTO toDTO(User user,String password){
        return UserDTO.builder()
                .uid(user.getUid())
                .name(user.getName())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .password(password)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

    }

}
