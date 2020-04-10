package classic_annotation;

@SuppressWarnings("unused")
class Car extends Vehicle{

    private final String model;


    public Car(FuelType fuelType, DriveType driveType, String model){
        super(fuelType, driveType);
        this.model = model;
    }

    @Override
    public void serviceVehicle(){
    }

    @Override
    public String toString(){
        return model;
    }
}
