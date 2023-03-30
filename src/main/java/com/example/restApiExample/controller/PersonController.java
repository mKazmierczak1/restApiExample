package com.example.restApiExample.controller;

import com.example.restApiExample.model.Person;
import com.example.restApiExample.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping("/people")
  List<Person> getAllPeople() {
    return personService.getAll();
  }

  @GetMapping("/person/{id}")
  Person getById(@PathVariable Integer id) {
    return personService.getById(id);
  }

  @PostMapping("/person")
  Person newPerson(@RequestBody Person newPerson) {
    return personService.put(newPerson);
  }

  @PutMapping("/person/{id}")
  Person updatePerson(@PathVariable Integer id, @RequestBody Person newPerson) {
    return personService.update(id, newPerson);
  }

  @DeleteMapping("/person/{id}")
  Person deletePerson(@PathVariable Integer id) {
    return personService.delete(id);
  }
}
