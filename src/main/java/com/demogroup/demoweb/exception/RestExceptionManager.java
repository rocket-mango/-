package com.demogroup.demoweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//restconrollerAdvice 어노테이션은 json 형식으로 response를 반환할 수 있다.
@RestControllerAdvice
public class RestExceptionManager {
    //appexception 핸들러
    @ExceptionHandler
    public ResponseEntity<?> appExceptionHandler(AppException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorCode().name()+" "+e.getMessage());
    }

    //@PasswordValid 어노테이션 에러났을 때 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validAnnotationHandler(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : "+e.getMessage());
    }
}
