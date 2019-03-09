package com.masglobal.recruitment.javatest.services.factories;

import com.masglobal.recruitment.javatest.dtos.ContractDto;
import com.masglobal.recruitment.javatest.dtos.HourlyContractDto;
import com.masglobal.recruitment.javatest.dtos.MonthlyContractDto;
import com.masglobal.recruitment.javatest.services.types.EContractType;
import lombok.Getter;
import org.springframework.beans.factory.FactoryBean;

public class ContractFactory implements FactoryBean<ContractDto> {
  
  @Getter
  private ContractDto currentContract;
  
  @Getter
  private final EContractType contractType;
  
  @Getter
  private final Integer hourlyAnnualSalary;
  
  @Getter
  private final Integer monthlyAnnualSalary;
  
  public ContractFactory(EContractType contractType, final Integer hourlyAnnualSalary, final Integer monthlyAnnualSalary) {
    this.contractType = contractType;
    this.hourlyAnnualSalary = hourlyAnnualSalary;
    this.monthlyAnnualSalary = monthlyAnnualSalary;
  }
  
  @Override
  public ContractDto getObject() {
    switch (contractType) {
      case HOURLY:
        currentContract = new HourlyContractDto();
        currentContract.setBaseSalary(currentContract.calcBaseFromAnnualSalary(hourlyAnnualSalary));
        break;
      case MONTHLY:
        currentContract = new MonthlyContractDto();
        currentContract.setBaseSalary(currentContract.calcBaseFromAnnualSalary(monthlyAnnualSalary));
        break;
      default:
        throw new IllegalArgumentException("No such contract type!");
    }
    currentContract.setName(contractType.getName());
    return currentContract;
  }
  
  @Override
  public Class<?> getObjectType() {
    return currentContract.getClass();
  }
}
