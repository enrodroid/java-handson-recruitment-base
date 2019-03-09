package com.masglobal.recruitment.javatest.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Integer id;
  
  private String name;
  
  private String description;
}
