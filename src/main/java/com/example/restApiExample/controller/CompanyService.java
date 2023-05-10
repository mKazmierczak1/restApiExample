package com.example.restApiExample.controller;

import com.example.restApiExample.dao.CompanyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyDao companyDao;

  public boolean companyExists(String name) {
    return companyDao.getByName(name).isPresent();
  }
}
