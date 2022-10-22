package com.rest.demo.restApi.payroll;

import com.rest.demo.restApi.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo
    extends JpaRepository<Employee, Long> {
}
