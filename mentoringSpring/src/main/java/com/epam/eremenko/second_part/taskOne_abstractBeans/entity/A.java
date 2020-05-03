package com.epam.eremenko.second_part.taskOne_abstractBeans.entity;


import java.io.Serializable;
import java.util.Objects;

public class A implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstProperty;
    private int secondProperty;

    public A() {
    }

    public String getFirstProperty() {
        return firstProperty;
    }

    public void setFirstProperty(String firstProperty) {
        this.firstProperty = firstProperty;
    }

    public int getSecondProperty() {
        return secondProperty;
    }

    public void setSecondProperty(int secondProperty) {
        this.secondProperty = secondProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof A)) return false;
        A a = (A) o;
        return secondProperty == a.secondProperty &&
                Objects.equals(firstProperty, a.firstProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstProperty, secondProperty);
    }

    @Override
    public String toString() {
        return "A{" +
                "firstProperty='" + firstProperty + '\'' +
                ", secondProperty=" + secondProperty +
                '}';
    }
}
