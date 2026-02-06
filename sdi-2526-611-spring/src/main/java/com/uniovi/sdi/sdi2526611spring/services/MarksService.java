package com.uniovi.sdi.sdi2526611spring.services;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import com.uniovi.sdi.sdi2526611spring.repositories.MarksRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    public List<Mark> getMarks() {
        List<Mark> marks=new ArrayList<Mark>();
        marksRepository.findAll().forEach(marks::add);
        return marks;
    }
    public Mark getMark(Long id) {
        return marksRepository.findById(id).orElse(null);
    }
    public void addMark(Mark mark) {
        marksRepository.save(mark);
    }
    public void deleteMark(Long id) {
       marksRepository.deleteById(id);
    }
}
