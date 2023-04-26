package com.example.restApiExample.service;

import com.example.restApiExample.dao.PersonDao;
import com.example.restApiExample.exeptions.PersonNotFoundException;
import com.example.restApiExample.model.Person;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonDao personDao;

  public Person getById(int id) throws PersonNotFoundException {
    return personDao.getById(id);
  }

  public List<Person> getAll() {
    return personDao.getAll();
  }

  public Person put(Person newPerson) {
    return personDao.put(newPerson);
  }

  public Person update(int id, Person newPerson) {
    return personDao.update(id, newPerson);
  }

  public Person delete(int id) {
    return personDao.delete(id);
  }
}
