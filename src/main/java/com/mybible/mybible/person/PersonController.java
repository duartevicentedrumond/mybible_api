package com.mybible.mybible.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public Person add(@RequestBody Person person){
        return personService.savePerson(person);

    }

    @GetMapping("/getAll")
    public List<Person> getAllPerson(){
        return personService.getAllPerson();
    }

    @RequestMapping("/{personId}")
    public Optional<Person> getPerson(@PathVariable Long personId){
        return personService.getPerson(personId);
    }

    @PutMapping("/update/{personId}")
    public Person updatePerson(@PathVariable Long personId, @RequestBody Person person){
        return personService.updatePerson(personId, person);
    }

    @DeleteMapping("/delete/{personId}")
    public void deletePerson(@PathVariable Long personId){
        personService.deletePerson(personId);
    }

}