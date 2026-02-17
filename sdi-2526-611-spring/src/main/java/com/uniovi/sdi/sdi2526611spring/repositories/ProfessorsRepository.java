package com.uniovi.sdi.sdi2526611spring.repositories;

import com.uniovi.sdi.sdi2526611spring.entities.Professor;
import org.springframework.data.repository.CrudRepository;

// Punto a: Uso de la interfaz CrudRepository
public interface ProfessorsRepository extends CrudRepository<Professor, Long> {

    Professor findByDni(String dni);
}