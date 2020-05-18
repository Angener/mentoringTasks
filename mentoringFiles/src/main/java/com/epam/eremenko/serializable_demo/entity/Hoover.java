package com.epam.eremenko.serializable_demo.entity;

import java.util.Objects;

public class Hoover extends Appliances {
    private static final long serialVersionUID = 1L;

    private String name;
    private String brand;
    private String originCountry;
    private boolean isPowerOn;

    public Hoover() {
        super();
    }

    public Hoover(float weigh, int voltage) {
        super(weigh, voltage);
    }

    public Hoover(float weigh, int voltage, String name,
                  String brand, String originCountry) {
        super(weigh, voltage);
        this.name = name;
        this.brand = brand;
        this.originCountry = originCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hoover)) return false;
        Hoover hoover = (Hoover) o;
        return isPowerOn == hoover.isPowerOn &&
                Objects.equals(name, hoover.name) &&
                Objects.equals(brand, hoover.brand) &&
                Objects.equals(originCountry, hoover.originCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, originCountry, isPowerOn);
    }

    @Override
    public String toString() {
        return "Hoover{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", isPowerOn=" + isPowerOn +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public boolean isPowerOn() {
        return isPowerOn;
    }

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }
}
