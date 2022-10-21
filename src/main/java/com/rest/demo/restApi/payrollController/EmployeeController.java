package com.rest.demo.restApi.payrollController;

import com.rest.demo.restApi.payrollRepo.Employee;
import com.rest.demo.restApi.payrollRepo.EmployeeRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {
  private final EmployeeRepo repo;
  private final EmployeeModelAssesmbler assembler;

  public EmployeeController(EmployeeRepo repo, EmployeeModelAssesmbler assembler) {
    this.repo = repo;
    this.assembler = assembler;
  }

  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all(){
  List<EntityModel<Employee>> employees = repo.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());

  return CollectionModel.of(employees,
      linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee){
    return repo.save(newEmployee);
  }

  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {

    Employee employee = repo.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    return assembler.toModel(employee);

  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    return repo.findById(id).map((employee) -> {
      employee.setName(newEmployee.getName());
      employee.setRole(newEmployee.getRole());
      return repo.save(employee);
    }).orElseGet(() -> {
      newEmployee.setId(id);
      return repo.save(newEmployee);
    });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repo.deleteById(id);
  }

}
