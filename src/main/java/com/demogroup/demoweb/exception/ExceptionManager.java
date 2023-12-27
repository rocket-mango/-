package com.demogroup.demoweb.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//restconrollerAdvice 어노테이션은 json 형식으로 response를 반환할 수 있다.
@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler
    public ResponseEntity<?> appExceptionHandler(AppException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode().name()+" "+e.getMessage());
    }
}
