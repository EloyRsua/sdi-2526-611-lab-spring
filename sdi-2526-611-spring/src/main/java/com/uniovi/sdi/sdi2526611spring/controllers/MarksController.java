package com.uniovi.sdi.sdi2526611spring.controllers;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import com.uniovi.sdi.sdi2526611spring.services.MarksService;
import com.uniovi.sdi.sdi2526611spring.services.UsersService;
import com.uniovi.sdi.sdi2526611spring.validators.MarksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarksController {
    private final MarksService marksService;
    private final UsersService usersService;
    @Autowired
    private MarksValidator marksValidator;

    public MarksController(MarksService marksService, UsersService usersService) {
        this.marksService = marksService;
        this.usersService = usersService;
    }

    @GetMapping("/mark/add")
    public String getMark(Model model) {
        model.addAttribute("usersList",usersService.getUsers());
        model.addAttribute("mark", new Mark());
        return "/mark/add";
    }

    @PostMapping("/mark/add")
    public String setMark(@ModelAttribute Mark mark, BindingResult result) {
        marksValidator.validate(mark, result);
        if (result.hasErrors()) {
            return "mark/add";
        }
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/list", method = RequestMethod.GET)
    public String getList(Model model){
        model.addAttribute("marksList",marksService.getMarks());
        return "/mark/list";
    }

    @GetMapping(value="/mark/details/{id}")
    public String getDetail(Model model,@PathVariable Long id) {
        model.addAttribute("mark",marksService.getMark(id));
        return "mark/details";
    }
    @RequestMapping ("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @GetMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }

    @PostMapping(value = "/mark/edit/{id}")
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id) {
        Mark originalMark = marksService.getMark(id);
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/" + id;
    }

    @GetMapping("/mark/list/update")
    public String updateList(Model model){
        model.addAttribute("marksList",marksService.getMarks());
        return "mark/list :: marksTable";
    }
}
