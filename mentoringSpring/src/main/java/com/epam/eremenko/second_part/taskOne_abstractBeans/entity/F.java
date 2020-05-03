package com.epam.eremenko.second_part.taskOne_abstractBeans.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

public class F implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(F.class);
    private String name;

    public F() {
        LOGGER.info("Constructor invoked");
    }

    public void init() {
        LOGGER.info("init-method invoked");
    }

    public void destroy() {
        LOGGER.info("destroy method invoked");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof F)) return false;
        F f = (F) o;
        return Objects.equals(name, f.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "F{" +
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
