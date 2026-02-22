package com.uniovi.sdi.sdi2526611spring.services;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import com.uniovi.sdi.sdi2526611spring.entities.User;
import com.uniovi.sdi.sdi2526611spring.repositories.MarksRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    /*
    * Es mejor inyectar las dependencias por constructor
    * */
    private final HttpSession httpSession;

    public MarksService(HttpSession httpSession){
        this.httpSession=httpSession;
    }

    public List<Mark> getMarks() {
        List<Mark> marks=new ArrayList<Mark>();
        marksRepository.findAll().forEach(marks::add);
        return marks;
    }
    public Mark getMark(Long id) {
        Mark mark = marksRepository.findById(id).isPresent() ? marksRepository.findById(id).get() : new Mark();
        return mark;
    }
    public void addMark(Mark mark) {
        marksRepository.save(mark);
    }
    public void deleteMark(Long id) {
       marksRepository.deleteById(id);
    }
    public void setMarkResend(boolean revised, Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        Mark mark = marksRepository.findById(id).isPresent() ? marksRepository.findById(id).get() : new Mark();
        if(mark.getUser().getDni().equals(dni) ) {
            marksRepository.updateResend(revised, id);
        }
    }
    public List<Mark> getMarksForUser(User user) {
        List<Mark> marks = new ArrayList<>();
        if (user.getRole().equals("ROLE_STUDENT")) {
            marks = marksRepository.findAllByUser(user);}
        if (user.getRole().equals("ROLE_PROFESSOR")) {
            marks = getMarks(); }
        return marks;
    }
}
