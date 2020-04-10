package custom_annotation.entity;

import custom_annotation.annotation.ProdCode;

import java.io.Serializable;
import java.util.Objects;

public class Appliance extends ElectricEquipment implements Serializable{

    private String category;
    private String function;

    public Appliance() {
    }

    public Appliance(float weigh, int voltage) {
        super(weigh, voltage);
    }

    @ProdCode
    private String getCategory(){
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appliance)) return false;
        Appliance appliance = (Appliance) o;
        return Objects.equals(category, appliance.category) &&
                Objects.equals(function, appliance.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, function);
    }
}