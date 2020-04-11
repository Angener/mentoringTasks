package handle_annotation.entity;


import handle_annotation.annotation.ThisCodeSmells;

public abstract class ElectricEquipment implements Cloneable {
    @ThisCodeSmells
    private float weigh;
    @ThisCodeSmells
    private int voltage;

    public ElectricEquipment() {
    }

    public ElectricEquipment(float weigh, int voltage) {
        this.voltage = voltage;
        this.weigh = weigh;
    }
}
