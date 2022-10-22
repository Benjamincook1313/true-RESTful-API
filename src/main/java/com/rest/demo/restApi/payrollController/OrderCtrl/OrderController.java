package com.rest.demo.restApi.payrollController.OrderCtrl;

import com.rest.demo.restApi.payroll.Status;
import com.rest.demo.restApi.payroll.entity.Order;
import com.rest.demo.restApi.payroll.repos.OrderRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderController {

  private final OrderRepo repo;
  private final OrderModelAssembler assembler;

  public OrderController(OrderRepo repo, OrderModelAssembler assembler) {
    this.repo = repo;
    this.assembler = assembler;
  }

  // endpoint for getting all orders
  @GetMapping("/orders")
  CollectionModel<EntityModel<Order>> all(){
    // create and structure Response data
    List<EntityModel<Order>> orders = repo.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(orders,
        linkTo(methodOn(OrderController.class).all()).withSelfRel());
  }

  @GetMapping("/orders/{id}")
  EntityModel<Order> one(@PathVariable Long id) {
    Order order = repo.findById(id)
        .orElseThrow(() -> new OrderNotFoundException(id));

    return assembler.toModel(order);
  }

  @PostMapping("orders")
  ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order){

    order.setStatus(Status.IN_PROGRESS);
    Order newOrder = repo.save(order);

    return ResponseEntity
        .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri())
        .body(assembler.toModel(newOrder));
  }

}
