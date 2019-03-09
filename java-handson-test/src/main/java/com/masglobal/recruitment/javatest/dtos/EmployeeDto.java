package com.masglobal.recruitment.javatest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private RoleDto role;
  
  private ContractDto contract;
}
