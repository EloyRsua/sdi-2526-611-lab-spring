package com.uniovi.sdi.sdi2526611spring.repositories;

import com.uniovi.sdi.sdi2526611spring.entities.Mark;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarksRepository extends CrudRepository<Mark, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
    void updateResend(Boolean resend, Long id);
}
