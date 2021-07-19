package com.filemarket.readwritedatabase.controller;

import com.filemarket.readwritedatabase.model.Person;
import com.filemarket.readwritedatabase.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ReadWriteController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/addPerson")
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        try {
            personService.addPerson(person);
            return (ResponseEntity<?>) ResponseEntity.ok().body(person);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error while adding person ::" + ex.toString());
        }
    }

    @GetMapping(value = "/listPersons")
    public List<Person> listPersons() {
        return (List<Person>) ResponseEntity.ok().body(personService.getPersonList());
    }

    @GetMapping(value = "/getPerson/{id}")
    public ResponseEntity<Optional<Person>> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok().body(personService.getPerson(id));
    }

    @PutMapping(value = "/updatePerson")
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        try {
            return (ResponseEntity<?>) ResponseEntity.ok().body(personService.updatePerson(person));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error while editing person::" + ex.toString());
        }
    }

    @DeleteMapping(value = "/deletePerson/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id) {
        try {
            personService.deletePerson(id);
            return (ResponseEntity<?>) ResponseEntity.ok();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error while deleting person::" + ex.toString());
        }
    }

}