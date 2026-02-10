package com.uniovi.sdi.sdi2526611spring.services;

import com.uniovi.sdi.sdi2526611spring.entities.Professor;
import com.uniovi.sdi.sdi2526611spring.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    @Autowired
    private ProfessorsRepository professorsRepository;

    public List<Professor> getProfessors() {
        List<Professor> professors = new ArrayList<>();
        professorsRepository.findAll().forEach(professors::add);
        return professors;
    }

    public Professor getProfessor(Long id) {
        return professorsRepository.findById(id).orElse(null);
    }

    public void addProfessor(Professor professor) {
        // save sirve tanto para insertar como para actualizar si el id existe
        professorsRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        professorsRepository.deleteById(id);
    }
}