package com.rest.demo.restApi.payrollController.EmployeeCtrl;

public class EmployeeNotFoundException extends RuntimeException {

  // Used to render a 404 error
  EmployeeNotFoundException(Long id) {
    super("could not find employee " + id);
  }

}
