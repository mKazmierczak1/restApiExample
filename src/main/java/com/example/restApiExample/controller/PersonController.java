package com.example.restApiExample.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.restApiExample.exeptions.PersonNotFoundException;
import com.example.restApiExample.model.Person;
import com.example.restApiExample.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping("/people")
  CollectionModel<Person> getAllPeople() {
    return CollectionModel.of(
        personService.getAll(),
        linkTo(methodOn(PersonController.class).getAllPeople()).withSelfRel());
  }

  @GetMapping("/person/{id}")
  public EntityModel<Person> getById(@PathVariable Integer id) throws PersonNotFoundException {
    return EntityModel.of(
        personService.getById(id),
        linkTo(methodOn(PersonController.class).getById(id)).withSelfRel(),
        linkTo(methodOn(PersonController.class).deletePerson(id)).withRel("delete"),
        linkTo(methodOn(PersonController.class).getAllPeople()).withRel("list all"));
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
