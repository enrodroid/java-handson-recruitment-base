package com.masglobal.recruitment.javatest.services;

import com.masglobal.recruitment.javatest.dtos.EmployeeDto;
import com.masglobal.recruitment.javatest.dtos.RoleDto;
import com.masglobal.recruitment.javatest.services.factories.ContractFactory;
import com.masglobal.recruitment.javatest.services.types.EContractType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceTest.class);
  
  @MockBean
  private EmployeeService employeeService;
  
  @Test
  public void getEmployeesTest() {
    LOGGER.debug("Testing Business Logic Layer: getEmployees()...");
    try {
      RoleDto enriqueRole = new RoleDto(0, "Support", "Support team member");
      EmployeeDto enrique = new EmployeeDto(0, "Enrique F. Agudo V.", enriqueRole,
          new ContractFactory(EContractType.HOURLY, 60000, 0).getObject());
      
      List<EmployeeDto> expectedList = Collections.singletonList(enrique);
      List<EmployeeDto> resultingList = employeeService.getEmployees();
      
      BDDMockito.given(resultingList).willReturn(expectedList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
