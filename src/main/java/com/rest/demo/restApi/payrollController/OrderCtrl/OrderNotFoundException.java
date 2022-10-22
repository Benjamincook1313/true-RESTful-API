package com.rest.demo.restApi.payrollController.OrderCtrl;

public class OrderNotFoundException extends RuntimeException{

  public OrderNotFoundException(Long id) {
    super("Could not find Order " + id);
  }
}
