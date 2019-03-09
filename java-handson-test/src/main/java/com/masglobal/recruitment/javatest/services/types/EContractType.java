package com.masglobal.recruitment.javatest.services.types;

import lombok.Getter;

import java.util.Arrays;

public enum EContractType {
  HOURLY (0, "HourlySalaryEmployee"),
  MONTHLY (1, "MonthlySalaryEmployee");
  
  @Getter
  private int code;
  
  @Getter
  private String name;
  
  EContractType(int code, String name) {
    this.code = code;
    this.name = name;
  }
  
  public static EContractType getByContractName(String contractTypeName) {
    return Arrays.stream(EContractType.values())
        .filter(ct -> ct.getName().equals(contractTypeName))
        .findFirst()
        .orElse(null);
  }
}
