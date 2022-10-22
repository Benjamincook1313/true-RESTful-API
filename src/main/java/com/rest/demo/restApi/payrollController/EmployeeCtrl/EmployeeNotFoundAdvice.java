package com.rest.demo.restApi.payrollController.EmployeeCtrl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmployeeNotFoundAdvice {

  @ResponseBody // Advice is rendered straight into Response body
  @ExceptionHandler(EmployeeNotFoundException.class) // only responds if the Class that is passed in is thrown
  @ResponseStatus(HttpStatus.NOT_FOUND) // issues status HTTP 404
  String employeeNotFoundHandler(EmployeeNotFoundException ex) {
    return ex.getMessage();
  }
}
