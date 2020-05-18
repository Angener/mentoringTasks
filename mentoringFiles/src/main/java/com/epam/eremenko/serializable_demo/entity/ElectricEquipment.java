package com.epam.eremenko.serializable_demo.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class ElectricEquipment implements Serializable {
    private static final long serialVersionUID = 1L;
    transient private float weigh;
    transient private int voltage;

    public ElectricEquipment() {
    }

    public ElectricEquipment(float weigh, int voltage) {
        this.voltage = voltage;
        this.weigh = weigh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectricEquipment)) return false;
        ElectricEquipment that = (ElectricEquipment) o;
        return Float.compare(that.weigh, weigh) == 0 &&
                voltage == that.voltage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weigh, voltage);
    }

    @Override
    public String toString() {
        return "ElectricEquipment{" +
                "weigh=" + weigh +
                ", voltage=" + voltage +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public float getWeigh() {
        return weigh;
    }

    public void setWeigh(float weigh) {
        this.weigh = weigh;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
}
