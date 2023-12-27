package com.demogroup.demoweb.controller;

import com.demogroup.demoweb.domain.User;
import com.demogroup.demoweb.domain.dto.UserDTO;
import com.demogroup.demoweb.exception.AppException;
import com.demogroup.demoweb.exception.ErrorCode;
import com.demogroup.demoweb.service.UserService;
import com.demogroup.demoweb.utils.MakeJsonUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody UserDTO dto, BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()){
            throw new AppException(ErrorCode.INVALID_PASSWORD,"비밀번호는 숫자,영문자(대소문자 가능),특수문자 조합의 8자 이상이어야 합니다.");
        }
        User joinUser = userService.join(dto);
        JSONObject jsonObject = MakeJsonUtil.makeJoinJson(joinUser.getUsername(), joinUser.getPassword());
        return ResponseEntity.ok().body(jsonObject);
    }






}

