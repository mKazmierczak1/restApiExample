package com.example.restApiExample.service;

import com.example.restApiExample.controller.CompanyService;
import com.example.restApiExample.dao.PersonDao;
import com.example.restApiExample.exeptions.CompanyNotFoundException;
import com.example.restApiExample.exeptions.PersonNotFoundException;
import com.example.restApiExample.model.Company;
import com.example.restApiExample.model.Person;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonDao personDao;
  private final CompanyService companyService;

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

  public Person update(int id, Company newCompany) {
    Person updatedPerson = getById(id);
    updatedPerson.setWorkplace(newCompany);

    return personDao.update(id, updatedPerson);
  }

  public Person delete(int id) {
    return personDao.delete(id);
  }

  public List<Person> getAllFromCompany(String companyName) {
    if (!companyService.companyExists(companyName)) {
      throw new CompanyNotFoundException();
    }

    return personDao.getAllFromCompany(companyName);
  }
}
