package com.rest.demo.restApi.payrollController.EmployeeCtrl;

import com.rest.demo.restApi.payroll.entity.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssesmbler
    implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

  // converts a non-model object (Employee) into a model-based object EntityModel<Employee>
  @Override
  public EntityModel<Employee> toModel(Employee employee) {
      return EntityModel.of(employee,
          // creates a url endpoint for each method on the class and adds it into the meta-data inside-of the Response
          linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
          linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }

}
