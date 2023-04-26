package com.example.restApiExample.dao;

import com.example.restApiExample.exeptions.PersonNotFoundException;
import com.example.restApiExample.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonDao {

  private final List<Person> people;

  public PersonDao(CompanyDao companyDao) {
    people = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      people.add(
          new Person(i, "firstName" + i, "lastName" + i, i + 20, companyDao.getById(i).get()));
    }
  }

  public Person getById(int id) throws PersonNotFoundException {
    return people.stream()
        .filter(person -> person.getId() == id)
        .findFirst()
        .orElseThrow(PersonNotFoundException::new);
  }

  public List<Person> getAll() {
    return people;
  }

  public Person put(Person newPerson) {
    people.add(newPerson);
    return newPerson;
  }

  public Person update(int id, Person newPerson) throws PersonNotFoundException {
    var personToUpdate = getById(id);

    personToUpdate.setFirstName(newPerson.getFirstName());
    personToUpdate.setLastName(newPerson.getLastName());
    personToUpdate.setAge(newPerson.getAge());
    personToUpdate.setWorkplace(newPerson.getWorkplace());

    return personToUpdate;
  }

  public Person delete(int id) throws PersonNotFoundException {
    var personToDelete = getById(id);
    people.remove(personToDelete);
    return personToDelete;
  }
}
