package com.uniovi.sdi.sdi2526611spring.repositories;

import com.uniovi.sdi.sdi2526611spring.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User,Long> {
}
