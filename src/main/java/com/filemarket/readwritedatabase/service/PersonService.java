package com.filemarket.readwritedatabase.service;

import com.filemarket.readwritedatabase.model.Person;
import com.filemarket.readwritedatabase.repository.readrepository.PersonReadRepository;
import com.filemarket.readwritedatabase.repository.writerepository.PersonWriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonReadRepository personReadRepository;
    private final PersonWriteRepository personWriteRepository;

    public PersonService(PersonReadRepository personReadRepository, PersonWriteRepository personWriteRepository) {
        this.personReadRepository = personReadRepository;
        this.personWriteRepository = personWriteRepository;
    }

    public Optional<Person> getPerson(Long id) {
        return personReadRepository.findById(id);
    }

    public List<Person> getPersonList() {
        return personReadRepository.findAll();
    }

    public Person addPerson(Person person) {
        Assert.notNull(person, "Invalid person");
        Assert.isNull(person.getId(), "person id should be null");
        Assert.notNull(person.getName(), "Invalid person name");
        return personWriteRepository.save(person);
    }

    public Person updatePerson(Person person) {
        Assert.notNull(person, "Invalid person");
        Assert.notNull(person.getId(), "Invalid person id");
        return personWriteRepository.save(person);
    }

    public void deletePerson(long id) {
        personWriteRepository.deleteById(id);
    }
}
