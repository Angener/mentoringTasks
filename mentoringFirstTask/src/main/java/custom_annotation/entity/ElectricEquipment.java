package custom_annotation.entity;


public abstract class ElectricEquipment implements Cloneable {
    private float weigh;
    private int voltage;

    public ElectricEquipment() {
    }

    public ElectricEquipment(float weigh, int voltage) {
        this.voltage = voltage;
        this.weigh = weigh;
    }
}
