package com.rest.demo.restApi.payroll.repos;

import com.rest.demo.restApi.payroll.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo
    extends JpaRepository<Order, Long> {
}
