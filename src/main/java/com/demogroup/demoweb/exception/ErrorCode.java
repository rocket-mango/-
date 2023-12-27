package com.demogroup.demoweb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND);
    private HttpStatus httpStatus;

}
