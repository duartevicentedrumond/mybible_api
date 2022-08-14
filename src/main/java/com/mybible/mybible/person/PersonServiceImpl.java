package com.mybible.mybible.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAllByOrderByPersonId();
    }

    @Override
    public Person getPerson(Long personId) {
        return personRepository.findByPersonId(personId);
    }

    @Override
    public Person updatePerson(Long personId, Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }
}
