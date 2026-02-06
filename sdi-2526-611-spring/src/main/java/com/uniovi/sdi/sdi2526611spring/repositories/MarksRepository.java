package com.uniovi.sdi.sdi2526611spring.repositories;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarksRepository extends CrudRepository<Mark, Long> {

}
