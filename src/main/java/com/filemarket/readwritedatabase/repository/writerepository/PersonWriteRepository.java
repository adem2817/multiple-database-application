package com.filemarket.readwritedatabase.repository.writerepository;

import com.filemarket.readwritedatabase.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonWriteRepository extends JpaRepository<Person, Long> {

}
