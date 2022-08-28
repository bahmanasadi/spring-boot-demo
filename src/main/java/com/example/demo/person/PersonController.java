package com.example.demo.person;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class PersonController {
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons")
    List<Person> all() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("persons/{id}")
    Person one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return repository.findById(id).map(person -> {
            person.setName(newPerson.getName());
            return repository.save(person);
        }).orElseGet(() -> {
            newPerson.setId(id);
            return repository.save(newPerson);
        });
    }
}
