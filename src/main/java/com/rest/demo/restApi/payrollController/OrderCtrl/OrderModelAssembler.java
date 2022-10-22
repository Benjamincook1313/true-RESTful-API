package com.rest.demo.restApi.payrollController.OrderCtrl;

import com.rest.demo.restApi.payroll.entity.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler
    implements RepresentationModelAssembler<Order, EntityModel<Order>> {


  @Override
  public EntityModel<Order> toModel(Order entity) {
    return null;
  }
}
