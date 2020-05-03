package com.epam.eremenko.taskOne_onlyXmlConfig.entity;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable, Comparable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Salary salary;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public Position(String name, Salary salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Position other = (Position) obj;
        return (name.equals(other.name) && salary.equals(other.salary));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary);
    }

    @Override
    public String toString() {
        return "Position: " + name + ". Salary: " + salary.getSalary() + ".";
    }

    public String getName() {
        return name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Object o) {
        Position other = (Position) o;
        if ( getName().equals(other.getName())){
            return 0;
        }
        return 1;
    }
}
