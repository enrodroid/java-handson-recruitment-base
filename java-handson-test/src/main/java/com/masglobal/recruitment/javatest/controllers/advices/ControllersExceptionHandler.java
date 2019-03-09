package com.masglobal.recruitment.javatest.controllers.advices;

import com.masglobal.recruitment.javatest.exceptions.HRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(value = {HRException.class})
  protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, ex.getMessage(),
        new HttpHeaders(), HttpStatus.PARTIAL_CONTENT, request);
  }
}
