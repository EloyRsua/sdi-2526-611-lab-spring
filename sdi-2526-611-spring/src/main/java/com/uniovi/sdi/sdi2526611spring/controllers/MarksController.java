package com.uniovi.sdi.sdi2526611spring.controllers;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @RequestMapping("/mark/list")
    public String getList(){
        return "Getting List";
    }

    @PostMapping("/mark/add")
    public String setMark(@ModelAttribute Mark mark) {
        return "Added: "+ mark.getDescription()+
                " with score: "+mark.getScore()+
                " id: "+mark.getId();
    }

    @GetMapping(value="/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return "Getting Details =>"+id;
    }
}
