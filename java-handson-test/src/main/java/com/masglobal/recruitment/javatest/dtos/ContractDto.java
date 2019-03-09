package com.masglobal.recruitment.javatest.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class ContractDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected Integer id;
  
  protected String name;
  
  @JsonFormat(shape = Shape.NUMBER_INT)
  protected Double baseSalary;
  
  @JsonProperty("annualSalary")
  @JsonFormat(shape = Shape.NUMBER_INT)
  public abstract Double calcAnnualSalary();
  
  @JsonIgnore
  public abstract Double calcBaseFromAnnualSalary(Integer originAnnualSalary);
}
