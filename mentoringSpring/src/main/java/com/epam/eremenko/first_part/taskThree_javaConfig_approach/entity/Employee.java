package com.epam.eremenko.first_part.taskThree_javaConfig_approach.entity;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    public static final long serialVersionUID = 1L;
    private String name;
    private Position position;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return (name.equals(other.getName()) &&
                position.equals(other.getPosition()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }

    @Override
    public String toString() {
        return "Employee: " + name + ". Position: " + position.getName() +
                ". Salary: " + position.getSalary().getSalary() + ".";
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
