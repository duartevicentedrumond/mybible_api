package com.mybible.mybible.person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);
    List<Person> getAllPerson();
    Person getPerson(Long personId);
    Person updatePerson(Long personId, Person person);
    void deletePerson(Long personId);
}
