package com.epam.eremenko.second_part.taskOne_abstractBeans.entity;

import java.io.Serializable;
import java.util.Objects;

public class D implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public D() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof D)) return false;
        D d = (D) o;
        return Objects.equals(name, d.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "D{" +
                "NAME='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
