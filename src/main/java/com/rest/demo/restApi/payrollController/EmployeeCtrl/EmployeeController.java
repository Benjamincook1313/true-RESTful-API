package com.rest.demo.restApi.payrollController.EmployeeCtrl;

import com.rest.demo.restApi.payroll.entity.Employee;
import com.rest.demo.restApi.payroll.EmployeeRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// spring mvc static helper methods
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController // data returned by each method will be written straight -
                // into the Response body instead of rendering a template
public class EmployeeController {
  private final EmployeeRepo repo;
  private final EmployeeModelAssesmbler assembler;

//  assembler allows us to convert non-model obj into a model-based obj
  public EmployeeController(EmployeeRepo repo, EmployeeModelAssesmbler assembler) {
    this.repo = repo;
    this.assembler = assembler;
  }

  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all(){
    /*
    Spring HATEOAS container;
    it’s aimed at encapsulating collections of resources—instead of a single resource entity,
    like EntityModel<> from earlier. CollectionModel<>, too, lets you include links.

    "encapsulating collections" means -> to encapsulate collections of employee resources
   */
  List<EntityModel<Employee>> employees = repo.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());

  return CollectionModel.of(employees,
      linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  @PostMapping("/employees")
  // ResponseEntity is used to create HTTP status 201
  ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){
    EntityModel<Employee> entityModel =
        assembler.toModel(repo.save(newEmployee));

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {

    Employee employee = repo.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    // using the assembler to create the model shown below
    return assembler.toModel(employee);
    /*
    Example of what a true RESTful API looks like with the meta-data
    {
      "id": 1,
      "name": "Bilbo Baggins",
      "role": "burglar",
      "_links": {
        "self": {
          "href": "http://localhost:8080/employees/1"
        },
        "employees": {
          "href": "http://localhost:8080/employees"
        }
      }
    }
    */
  }

  @PutMapping("/employees/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    Employee updatedEmployee =  repo.findById(id).map((employee) -> {
      employee.setFirstName(newEmployee.getFirstName());
      employee.setLastName(newEmployee.getLastName());
      employee.setRole(newEmployee.getRole());
      return repo.save(employee);
    }).orElseGet(() -> {
      newEmployee.setId(id);
      return repo.save(newEmployee);
    });

    /*
      The Employee object built from the save() operation is then wrapped using the EmployeeModelAssembler
      into an EntityModel<Employee> object. Using the getRequiredLink() method,
      you can retrieve the Link created by the EmployeeModelAssembler with a SELF rel.
      This method returns a Link which must be turned into a URI with the toUri method.
    */

    EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/employees/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    repo.deleteById(id);
    // This returns an HTTP 204 No Content response.
    return ResponseEntity.noContent().build();
  }

}
