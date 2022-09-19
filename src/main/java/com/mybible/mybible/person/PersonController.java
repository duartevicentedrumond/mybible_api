package com.mybible.mybible.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

        List<Person> allPeople = personService.getAllPerson();
        LocalDate currentDate = LocalDate.now();

        allPeople.forEach( person -> {

            Date birthday = person.getBirthday();

            if (birthday != null ) {
                LocalDate birthdayLocalDate = Instant.ofEpochMilli(birthday.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                Long age = Long.valueOf(Period.between(birthdayLocalDate, currentDate).getYears());
                person.setAge(age);
            }

        } );

        return personService.getAllPerson();
    }

    @RequestMapping("/{personId}")
    public Person getPerson(@PathVariable Long personId){
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