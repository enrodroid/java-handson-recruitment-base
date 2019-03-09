package com.masglobal.recruitment.javatest.dataaccess.impls;

import com.masglobal.recruitment.javatest.dataaccess.EmployeeDao;
import com.masglobal.recruitment.javatest.dataaccess.dtos.EmployeeDaoDto;
import com.masglobal.recruitment.javatest.dtos.EmployeeDto;
import com.masglobal.recruitment.javatest.dtos.RoleDto;
import com.masglobal.recruitment.javatest.exceptions.HRDaoException;
import com.masglobal.recruitment.javatest.services.factories.ContractFactory;
import com.masglobal.recruitment.javatest.services.types.EContractType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);
  
  private static final String REST_API_URL = "datasource.rest.api.url";
  
  private final RestTemplate restTemplate;
  
  private final String datasourceApiUrl;
  
  private List<EmployeeDaoDto> employeesData;
  
  @Autowired
  public EmployeeDaoImpl(RestTemplate restTemplate, Environment environment) {
    this.restTemplate = restTemplate;
    this.datasourceApiUrl = environment.getRequiredProperty(REST_API_URL);
  }
  
  @Override
  public EmployeeDto findById(Integer id) throws HRDaoException {
    LOGGER.debug("Looking for employee with id '{}'...", id);
    try {
      checkDataLoadStatus();
      EmployeeDaoDto eDDto = employeesData.stream()
          .filter(e -> e.getId().equals(id))
          .findFirst()
          .orElse(null);
      
      if (Objects.nonNull(eDDto)) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(eDDto.getId());
        employeeDto.setName(eDDto.getName());
        employeeDto.setRole(new RoleDto(eDDto.getRoleId(), eDDto.getRoleName(), eDDto.getRoleDescription()));
        employeeDto.setContract(new ContractFactory(EContractType.getByContractName(eDDto.getContractTypeName()),
            eDDto.getHourlySalary(), eDDto.getMonthlySalary()).getObject());
        
        return employeeDto;
      }
      
      return null;
    } catch (Exception e) {
      LOGGER.error("Datasource searching for employee with id '" + id + "' has fail!", e);
      throw new HRDaoException("Remote data loading for employee with id " + id + " has fail!");
    }
  }
  
  @Override
  public List<EmployeeDto> findAll() throws HRDaoException {
    LOGGER.debug("Getting all employees from origin...");
    try {
      checkDataLoadStatus();
      List<EmployeeDto> employees = new LinkedList<>();
      employeesData.forEach(eDDto -> {
        EmployeeDto businessEmployee = new EmployeeDto();
        businessEmployee.setId(eDDto.getId());
        businessEmployee.setName(eDDto.getName());
        businessEmployee.setRole(new RoleDto(eDDto.getRoleId(), eDDto.getRoleName(), eDDto.getRoleDescription()));
        
        EContractType contractType = EContractType.getByContractName(eDDto.getContractTypeName());
        businessEmployee.setContract(
            new ContractFactory(contractType, eDDto.getHourlySalary(),
                eDDto.getMonthlySalary()).getObject()
        );
        employees.add(businessEmployee);
      });
      return employees;
    } catch (Exception e) {
      LOGGER.error("Datasource loading execution has fail!", e);
      throw new HRDaoException("Remote data loading for all employees has fail!");
    }
  }
  
  private List<EmployeeDaoDto> fetchDataFromRemoteApi() {
    LOGGER.debug("Fetching employee data from an external API from: {}", datasourceApiUrl);
    
    ResponseEntity<EmployeeDaoDto[]> response = restTemplate.getForEntity(datasourceApiUrl, EmployeeDaoDto[].class);
    EmployeeDaoDto[] employeeData = response.getBody();
    
    if (Objects.nonNull(employeeData)) {
      LOGGER.debug("Found {} employees", employeeData.length);
      return Arrays.asList(employeeData);
    }
    return Collections.emptyList();
  }
  
  private void checkDataLoadStatus() {
    if (Objects.isNull(employeesData) || employeesData.equals(Collections.emptyList())) {
      employeesData = fetchDataFromRemoteApi();
    }
  }
}
