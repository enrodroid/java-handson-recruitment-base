package com.masglobal.recruitment.javatest.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MonthlyContractDto extends ContractDto {
  private static final long serialVersionUID = 1L;
  
  @Override
  public Double calcAnnualSalary() {
    return this.baseSalary * 12;
  }
  
  @Override
  public Double calcBaseFromAnnualSalary(Integer originAnnualSalary) {
    this.baseSalary = originAnnualSalary / 12D;
    return this.baseSalary > 0 ? this.baseSalary : 0;
  }
}
