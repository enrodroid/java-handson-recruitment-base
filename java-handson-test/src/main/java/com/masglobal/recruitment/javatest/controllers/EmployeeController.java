package com.masglobal.recruitment.javatest.controllers;

import com.masglobal.recruitment.javatest.dtos.EmployeeDto;
import com.masglobal.recruitment.javatest.exceptions.HRException;
import com.masglobal.recruitment.javatest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr-api/employees")
public class EmployeeController {
  
  private EmployeeService employeeService;
  
  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }
  
  @CrossOrigin
  @GetMapping("/")
  public List<EmployeeDto> getEmployees() throws HRException {
    return employeeService.getEmployees();
  }
  
  @CrossOrigin
  @GetMapping("/{employeeId}")
  public EmployeeDto getEmployeeById(@PathVariable Integer employeeId) throws HRException {
    return employeeService.getEmployee(employeeId);
  }
}
