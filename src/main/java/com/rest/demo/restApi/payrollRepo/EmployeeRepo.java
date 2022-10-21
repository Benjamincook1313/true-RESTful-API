package com.rest.demo.restApi.payrollRepo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo
    extends JpaRepository<Employee, Long> {
}
