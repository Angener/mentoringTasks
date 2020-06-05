package com.eremenko.task_two.entity;

import com.eremenko.task_two.dao.DaoTransferObject;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements DaoTransferObject, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String TYPE = "customers";

    private int id;
    private String name;

    public Customer(){}

    public Customer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
