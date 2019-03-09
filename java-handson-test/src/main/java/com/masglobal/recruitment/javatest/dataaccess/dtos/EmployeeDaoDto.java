package com.masglobal.recruitment.javatest.dataaccess.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDaoDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private Integer roleId;
  
  private String roleName;
  
  private String roleDescription;
  
  private String contractTypeName;
  
  private Integer hourlySalary;
  
  private Integer monthlySalary;
}
