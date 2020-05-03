package com.epam.eremenko.second_part.taskOne_abstractBeans.entity;

import java.io.Serializable;
import java.util.Objects;

public class C implements Serializable {
    private final static long serialVersionUID = 1L;
    private String name;
    private D d;

    public C() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof C)) return false;
        C c = (C) o;
        return Objects.equals(name, c.name) &&
                Objects.equals(d, c.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, d);
    }

    @Override
    public String toString() {
        return "C{" +
                "name='" + name + '\'' +
                ", d=" + d +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }
}
