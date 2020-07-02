package com.excilys.projet.java.cdb.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.projet.java.cdb.dto.ComputerDTO;

public class ValidatorDTO implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return ComputerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerDTO computerDTO = (ComputerDTO) target;
        System.out.println("test");
        if (computerDTO.getName() == null || computerDTO.getName().isEmpty()) {
            errors.rejectValue("name", "null or empty");
        }
        if (computerDTO.getName().equals("test")) {
            errors.rejectValue("name", "test");
        }
    }
}