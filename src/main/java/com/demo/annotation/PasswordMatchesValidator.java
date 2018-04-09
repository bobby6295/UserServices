package com.demo.annotation;

import com.demo.DTO.ResetDto;
import com.demo.DTO.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {






    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

        //initialize method run for initialize email by default
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        UserDto userDto;
        ResetDto resetDto;
        if (value instanceof UserDto)
        {
         userDto=  (UserDto)value;
            return userDto.getPassword().equals(userDto.getMatchingPassword());
        }
        else if (value instanceof ResetDto)
        {
            resetDto=  (ResetDto) value;
            return resetDto.getPassword().equals(resetDto.getConfirmPassword());

        }

        return  false;
    }
}
