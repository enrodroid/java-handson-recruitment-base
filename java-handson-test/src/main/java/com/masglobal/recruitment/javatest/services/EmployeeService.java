package com.masglobal.recruitment.javatest.services;

import com.masglobal.recruitment.javatest.dtos.EmployeeDto;
import com.masglobal.recruitment.javatest.exceptions.HRException;

import java.util.List;

public interface EmployeeService {

  EmployeeDto getEmployee(Integer id) throws HRException;
  
  List<EmployeeDto> getEmployees() throws HRException;
}
