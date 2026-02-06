package com.uniovi.sdi.sdi2526611spring.controllers;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import com.uniovi.sdi.sdi2526611spring.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {
    @Autowired
    private MarksService marksService;

    @RequestMapping(value = "/mark/list", method = RequestMethod.GET)
    public String getList(){
        return marksService.getMarks().toString();
    }

    @PostMapping("/mark/add")
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "Mark added";
    }

    @GetMapping(value="/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }
    @RequestMapping ("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        marksService.deleteMark(id);
        return "Mark deleted";
    }
}
