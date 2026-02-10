package com.uniovi.sdi.sdi2526611spring.controllers;

import com.uniovi.sdi.sdi2526611spring.entities.Professor;
import com.uniovi.sdi.sdi2526611spring.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorsController {

    @Autowired
    private ProfessorsService professorsService;

    @GetMapping("/professor/list")
    public String getList() {
        return "Lista de profesores: " + professorsService.getProfessors().toString();
    }

    @GetMapping("/professor/add")
    public String getAdd() {
        return "Formulario de añadir profesor";
    }

    @PostMapping("/professor/add")
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "Nuevo profesor añadido: " + professor.getNombre();
    }

    @GetMapping("/professor/details/{id}")
    public String getDetail(@PathVariable Long id) {
        Professor p = professorsService.getProfessor(id);
        return "Detalles del profesor: " + (p != null ? p.getNombre() : "No encontrado");
    }

    @GetMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorsService.deleteProfessor(id);
        return "Profesor eliminado: " + id;
    }

    @GetMapping("/professor/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return "Editando al profesor con ID: " + id;
    }

    @PostMapping("/professor/edit/{id}")
    public String setEdit(@ModelAttribute Professor professor, @PathVariable Long id) {
        professor.setId(id);
        professorsService.addProfessor(professor);
        return "Profesor editado correctamente: " + professor.getNombre();
    }
}