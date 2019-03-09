package com.masglobal.recruitment.javatest.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HourlyContractDto extends ContractDto {
  private static final long serialVersionUID = 1L;
  
  @Override
  public Double calcAnnualSalary() {
    // 120 * salary * 12
    return this.baseSalary * 1440;
  }
  
  @Override
  public Double calcBaseFromAnnualSalary(Integer originAnnualSalary) {
    this.baseSalary = originAnnualSalary / 1440D;
    return this.baseSalary > 0 ? this.baseSalary : 0;
  }
}
