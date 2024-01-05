package com.demogroup.demoweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//public class ExceptionManager {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> validAnnotationHandler(MethodArgumentNotValidException e){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : "+e.getMessage());
//    }
//}
