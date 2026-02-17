package com.uniovi.sdi.sdi2526611spring.validators;

import com.uniovi.sdi.sdi2526611spring.entities.Professor;
import com.uniovi.sdi.sdi2526611spring.entities.User;
import com.uniovi.sdi.sdi2526611spring.services.ProfessorsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfessorsValidator implements Validator {

    private final ProfessorsService professorsService;

    public ProfessorsValidator(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Professor.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        String dni = professor.getDni();

        // Validación básica de campos vacíos
        if (professor.getNombre().isEmpty()) errors.rejectValue("nombre", "Error.empty");
        if (professor.getApellido().isEmpty()) errors.rejectValue("apellido", "Error.empty");

        // Validación de DNI por pasos (8 números + 1 letra)
        if (dni == null || dni.length() != 9) {
            errors.rejectValue("dni", "Error.professor.dni.format");
        }
        if (professorsService.getProfessorByDni(dni) != null) {
            errors.rejectValue("dni", "Error.professor.dni.duplicate");
        }
        else {
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(dni.charAt(i))) {
                    errors.rejectValue("dni", "Error.professor.dni.format");
                    return;
                }
            }
            char lastChar = dni.charAt(8);
            if (!Character.isLetter(lastChar) || !Character.isUpperCase(lastChar)) {
                errors.rejectValue("dni", "Error.professor.dni.format");
            }
        }
    }
}