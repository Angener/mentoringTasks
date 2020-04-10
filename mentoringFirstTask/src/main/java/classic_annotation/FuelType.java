package classic_annotation;

public enum FuelType {
    GAS("Gas"),
    DIESEL("Diesel");

    private String fuelType;

    FuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }
}
