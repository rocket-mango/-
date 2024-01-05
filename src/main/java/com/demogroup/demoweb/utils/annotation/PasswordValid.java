package com.demogroup.demoweb.utils.annotation;

import com.demogroup.demoweb.utils.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //javadoc에 저장하여 어노테이션으로 사용할 수 있도록 한다.
@Constraint(validatedBy = PasswordValidator.class) //어떤 class로 검증을 진행할 것인지 지정
@Target({ElementType.FIELD, ElementType.PARAMETER}) //이 어노테이션을 DTO의 필드에 붙일 수 있고, 메소드의 파라이터에 넣을 수도 있다.
@Retention(RetentionPolicy.RUNTIME) //이 어노테이션 관리는 runtime동안 지속
public @interface PasswordValid {
    String message() default "유효하지 않은 비밀번호입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
