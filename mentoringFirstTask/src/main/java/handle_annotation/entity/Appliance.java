package handle_annotation.entity;

import handle_annotation.annotation.ProdCode;
import custom_annotation.entity.ElectricEquipment;
import handle_annotation.annotation.ThisCodeSmells;

import java.io.Serializable;
import java.util.Objects;

@ThisCodeSmells
public class Appliance extends ElectricEquipment implements Serializable {

    private String category;
    @ThisCodeSmells
    private String function;

    public Appliance() {
    }

    public Appliance(float weigh, int voltage) {
        super(weigh, voltage);
    }

    @ThisCodeSmells
    private String getCategory() {
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

    @ProdCode
    private void introduce() {
        System.out.println("I'm a appliance");
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, function);
    }
}