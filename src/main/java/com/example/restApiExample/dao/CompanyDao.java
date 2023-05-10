package com.example.restApiExample.dao;

import com.example.restApiExample.model.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CompanyDao {

  private final List<Company> companies;

  public CompanyDao() {
    companies = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      companies.add(new Company(i, "company" + i, "city" + i, "street" + i, i + "A/23"));
    }
  }

  public Optional<Company> getById(int id) {
    return companies.stream().filter(company -> company.getId() == id).findFirst();
  }

  public Optional<Company> getByName(String name) {
    return companies.stream()
        .filter(company -> company.getName().equalsIgnoreCase(name))
        .findFirst();
  }
}
