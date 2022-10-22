package com.rest.demo.restApi.payroll.entity;

import com.rest.demo.restApi.payroll.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {

  @Id
  @GeneratedValue
  private Long id;
  private Status status;

  Order() {};

  public Order(Long id, Status status) {
    this.id = id;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id) && Objects.equals(status, order.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", status=" + status +
        '}';
  }
}
