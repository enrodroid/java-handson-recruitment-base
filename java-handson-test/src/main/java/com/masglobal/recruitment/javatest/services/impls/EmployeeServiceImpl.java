package com.masglobal.recruitment.javatest.services.impls;

import com.masglobal.recruitment.javatest.dataaccess.EmployeeDao;
import com.masglobal.recruitment.javatest.dtos.EmployeeDto;
import com.masglobal.recruitment.javatest.exceptions.HRDaoException;
import com.masglobal.recruitment.javatest.exceptions.HRException;
import com.masglobal.recruitment.javatest.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  
  private EmployeeDao employeeDao;
  
  @Autowired
  public EmployeeServiceImpl(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }
  
  @Override
  public EmployeeDto getEmployee(Integer id) throws HRException {
    try {
      EmployeeDto employeeDto = employeeDao.findById(id);
      if (Objects.nonNull(employeeDto)) {
        return employeeDto;
      }
    } catch (HRDaoException e) {
      LOGGER.error("Exception was triggered trying to find employee with id " + id, e);
    }
    throw new HRException("Employee not found!");
  }
  
  @Override
  public List<EmployeeDto> getEmployees() throws HRException {
    try {
      return employeeDao.findAll();
    } catch (HRDaoException e) {
      LOGGER.error("Exception was triggered loading all employees!", e);
      throw new HRException("Wasn't possible to retrieve all employees!");
    }
  }
}
