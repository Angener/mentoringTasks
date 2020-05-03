package com.epam.eremenko.taskTwo_annotationBased_approach.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
@Scope("prototype")
public class Salary implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double salary;

    public Salary() {
    }

    public Salary(String name) {
        this.name = name;
    }

    public Salary(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Salary other = (Salary) obj;
        return (name.equals(other.name) && salary == other.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }

    @Override
    public String toString() {
        return "Salary " + name + ": " + salary + ".";
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
