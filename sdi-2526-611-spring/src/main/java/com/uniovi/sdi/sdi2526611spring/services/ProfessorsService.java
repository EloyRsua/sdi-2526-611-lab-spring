package com.uniovi.sdi.sdi2526611spring.services;

import com.uniovi.sdi.sdi2526611spring.entities.Professor;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfessorsService {
    private List<Professor> professorsList = new LinkedList<>();

    @PostConstruct
    public void init() {
        professorsList.add(new Professor(1L,"1234A" ,"Enrique", "De La Cal", "Titular"));
        professorsList.add(new Professor(2L,"5678B" ,"Eloy", "Rubio", "Asociado"));

    }

    public List<Professor> getProfessors() {
        return professorsList;
    }

    public void addProfessor(Professor professor) {
        if (professor.getId() == null) {
            professor.setId(professorsList.get(professorsList.size() - 1).getId() + 1);
        }
        professorsList.add(professor);
    }

    public Professor getProfessor(Long id) {
        return professorsList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteProfessor(Long id) {
        professorsList.removeIf(p -> p.getId().equals(id));
    }
}