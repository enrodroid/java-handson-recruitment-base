package com.masglobal.recruitment.javatest.dataaccess;

import com.masglobal.recruitment.javatest.dtos.EmployeeDto;
import com.masglobal.recruitment.javatest.exceptions.HRDaoException;

import java.util.List;

public interface EmployeeDao {
  
  EmployeeDto findById(Integer id) throws HRDaoException;
  
  List<EmployeeDto> findAll() throws HRDaoException;
}
