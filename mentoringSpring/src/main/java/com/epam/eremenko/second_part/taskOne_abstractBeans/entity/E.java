package com.epam.eremenko.second_part.taskOne_abstractBeans.entity;

import java.io.Serializable;
import java.util.Objects;

public class E implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public E() {
    }

    public String introduce() {
        return "hello";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof E)) return false;
        E e = (E) o;
        return Objects.equals(name, e.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "E{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
