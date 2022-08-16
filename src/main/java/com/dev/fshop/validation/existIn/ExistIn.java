package com.dev.fshop.validation.existIn;

import com.dev.fshop.validation.existIn.ExistInValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = ExistInValidator.class)
@Retention(RUNTIME)
public @interface ExistIn {
    String message() default "This value is invalid!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName() default "";
    String className() default "";
}