package com.uniovi.sdi.sdi2526611spring.services;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import com.uniovi.sdi.sdi2526611spring.entities.User;
import com.uniovi.sdi.sdi2526611spring.repositories.MarksRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<Mark> getMarks(Pageable pageable) {
        return marksRepository.findAll(pageable);
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
    public Page<Mark> getMarksForUser(Pageable pageable, User user) {
        // Usamos equalsIgnoreCase para evitar fallos por mayúsculas/minúsculas
        if (user.getRole().equalsIgnoreCase("ROLE_PROFESSOR")) {
            // IMPORTANTE: Asegúrate de que marksRepository.findAll(pageable) no sea null
            return marksRepository.findAll(pageable);
        }
        if (user.getRole().equalsIgnoreCase("ROLE_STUDENT")) {
            return marksRepository.findAllByUser(pageable, user);
        }
        // SIEMPRE devuelve una página vacía en lugar de null para que la vista no explote
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }

    public Page<Mark> searchMarksByDescriptionAndNameForUser(Pageable pageable, String searchText, User user)
    {
        Page<Mark> marks = new PageImpl<>(new LinkedList<>());
        searchText = "%" + searchText + "%";
        if (user.getRole().equals("ROLE_STUDENT")) {
            marks = marksRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
        }
        if (user.getRole().equals("ROLE_PROFESSOR")) {
            marks = marksRepository.searchByDescriptionAndName(pageable, searchText);
        }
        return marks;
    }
}
