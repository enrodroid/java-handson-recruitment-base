package com.masglobal.recruitment.javatest.exceptions;

public class HRDaoException extends HRException {
  
  public HRDaoException(String message) {
    super(message);
  }
  
  public HRDaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
