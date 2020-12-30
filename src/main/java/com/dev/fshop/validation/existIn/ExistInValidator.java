package com.dev.fshop.validation.existIn;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistInValidator implements ConstraintValidator<ExistIn, String> {
    @Autowired
    private ExistInServiceImp service;

    private String fieldName;
    private String className;

    @Override
    public void initialize(ExistIn constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        className = constraintAnnotation.className();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(service == null){
            return true;
        }
        return service.existIn(this.fieldName, s, this.className);
    }
}
