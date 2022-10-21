package com.rest.demo.restApi.payrollController;

public class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(Long id) {
    super("could not find employee " + id);
  }

}
