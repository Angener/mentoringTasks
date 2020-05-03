package com.epam.eremenko.second_part.taskOne_abstractBeans.entity;

import java.io.Serializable;
import java.util.Objects;

public class B implements Serializable {
    private final static long serialVersionUID = 1L;
    private String NAME;
    private A a;

    public B() {
    }

    public B(String NAME, A a) {
        this.NAME = NAME;
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof B)) return false;
        B b = (B) o;
        return Objects.equals(NAME, b.NAME) &&
                Objects.equals(a, b.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME, a);
    }

    @Override
    public String toString() {
        return "B{" +
                "NAME='" + NAME + '\'' +
                ", a=" + a +
                '}';
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
