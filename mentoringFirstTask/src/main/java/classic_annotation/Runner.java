package classic_annotation;

class Runner {

    public static void main(String[] args) throws Exception {
        Vehicle audi = new Car(FuelType.DIESEL, DriveType.AWD, "Audi a6");
        @SuppressWarnings("all")
        Vehicle infiniti = new Car(FuelType.GAS, DriveType.AWD, "Infiniti FX35");

        System.out.println(Util.getParametrizedVehicle(audi, (car) ->
                car.isFullDrive() && car.isGasEngine()));
        System.out.println(Util.getParametrizedVehicle(infiniti, (car) ->
                car.isFullDrive() && car.isGasEngine()));

        ServiceStation serviceStation = new ServiceStation();
        infiniti.setTankCapacity(100);
        infiniti.setFuelRemaining(80);
        serviceStation.serviceVehicle(infiniti);
    }
}
