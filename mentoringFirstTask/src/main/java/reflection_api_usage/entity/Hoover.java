package reflection_api_usage.entity;

import java.util.Objects;

public class Hoover extends Appliance {
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
}
