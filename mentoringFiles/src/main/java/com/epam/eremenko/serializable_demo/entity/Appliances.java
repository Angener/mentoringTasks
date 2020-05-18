package com.epam.eremenko.serializable_demo.entity;

import java.util.Objects;

public class Appliances extends ElectricEquipment {
    private static final long serialVersionUID = 1L;

    private String category;
    private String function;

    public Appliances() {
    }

    public Appliances(float weigh, int voltage) {
        super(weigh, voltage);
    }

    private String getCategory(){
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appliances)) return false;
        Appliances appliance = (Appliances) o;
        return Objects.equals(category, appliance.category) &&
                Objects.equals(function, appliance.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, function);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Appliances{" +
                "category='" + category + '\'' +
                ", function='" + function + '\'' +
                '}';
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
