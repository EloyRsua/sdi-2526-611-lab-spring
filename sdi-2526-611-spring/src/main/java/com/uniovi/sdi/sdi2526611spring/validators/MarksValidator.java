package com.uniovi.sdi.sdi2526611spring.validators;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MarksValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Mark.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;

        if (mark.getScore() == null || mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.mark.score.range");
        }

        if (mark.getDescription() == null || mark.getDescription().length() < 20) {
            errors.rejectValue("description", "Error.mark.description.size");
        }
    }
}
