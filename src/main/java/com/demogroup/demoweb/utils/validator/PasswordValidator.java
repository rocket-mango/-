package com.demogroup.demoweb.utils.validator;

import com.demogroup.demoweb.utils.annotation.PasswordValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

//커스텀 어노테이션이 어떻게 유효성 검사를 수행할지 정의해놓은 클래스
public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    private static final String PASSWORD_PATTERN="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";

    @Override
    public void initialize(PasswordValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return Pattern.matches(PASSWORD_PATTERN,password);
    }
}
