package com.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator  implements ConstraintValidator<ContactNumberConstraint ,String>{





    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {

       // aswdasd asdas dasd
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {
        return  contactField!=null&&contactField.matches("[0-9]+")&&(contactField.length()>8)&&contactField.length()<14;
    }
}
