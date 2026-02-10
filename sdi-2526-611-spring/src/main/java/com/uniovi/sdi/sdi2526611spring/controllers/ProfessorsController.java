package com.uniovi.sdi.sdi2526611spring.controllers;

import com.uniovi.sdi.sdi2526611spring.entities.Professor;
import com.uniovi.sdi.sdi2526611spring.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ProfessorsController {

    @Autowired
    private ProfessorsService professorsService;

    @GetMapping("/professor/list")
    public String getList(Model model) {
        model.addAttribute("professorList", professorsService.getProfessors());
        return "professor/list";
    }

    @GetMapping("/professor/add")
    public String getAdd() {
        return "professor/add";
    }

    @PostMapping("/professor/add")
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "redirect:/professor/list"; // Redirige a la lista actualizada
    }

    @GetMapping("/professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("professor", professorsService.getProfessor(id));
        return "professor/details";
    }

    @GetMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorsService.deleteProfessor(id);
        return "redirect:/professor/list";
    }

    @GetMapping("/professor/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return "/professor/edit/{id}" ;
    }

    @PostMapping("/professor/edit/{id}")
    public String setEdit(@ModelAttribute Professor professor, @PathVariable Long id) {
        professor.setId(id);
        professorsService.addProfessor(professor);
        return "redirect:/professor/list";
    }
}