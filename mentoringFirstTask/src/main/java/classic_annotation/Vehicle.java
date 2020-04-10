package classic_annotation;

abstract class Vehicle {

    private final String FUEL_TYPE;
    private final String DRIVE_TYPE;
    private float TankCapacity;
    private float fuelRemaining;

    public Vehicle(FuelType fuelType, DriveType driveType) {
        this.FUEL_TYPE = fuelType.getFuelType();
        this.DRIVE_TYPE = driveType.getDriveType();
    }

    public String getFUEL_TYPE() {
        return FUEL_TYPE;
    }

    public String getDRIVE_TYPE() {
        return DRIVE_TYPE;
    }

    public void setTankCapacity(float tankCapacity) {
        TankCapacity = tankCapacity;
    }

    public void setFuelRemaining(float fuelRemaining) {
        this.fuelRemaining = fuelRemaining;
    }

    @Deprecated
    /* Since version 1.1, the ServiceStation class is used for maintenance **/
    public abstract void serviceVehicle();

    public void replenishTank() {
        fuelRemaining += getEmptyTankCapacity();
    }

    public float getEmptyTankCapacity() {
        return TankCapacity - fuelRemaining;
    }

    public boolean isFullDrive() {
        return DRIVE_TYPE.equals(DriveType.AWD.getDriveType());
    }

    public boolean isGasEngine() {
        return FUEL_TYPE.equals(FuelType.GAS.getFuelType());
    }
}
