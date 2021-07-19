package com.filemarket.readwritedatabase.repository.readrepository;

import com.filemarket.readwritedatabase.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonReadRepository extends JpaRepository<Person, Long> {

}
