package classic_annotation;

@SuppressWarnings("unused")
class ServiceStation {
    private static double gasGallonsCounter;
    private static double dieselGallonsCounter;

    public void serviceVehicle(Vehicle vehicle) throws Exception {
        updateFuelCounter(vehicle);
        replenishTank(vehicle);
    }

    private void updateFuelCounter(Vehicle vehicle) throws Exception {
        switch (vehicle.getFUEL_TYPE()) {
            case "Gas" -> gasGallonsCounter += vehicle.getEmptyTankCapacity();
            case "Diesel" -> dieselGallonsCounter += vehicle.getEmptyTankCapacity();
            default -> throw new Exception("Service impossible");
        }
    }

    private void replenishTank(Vehicle vehicle) {
        vehicle.replenishTank();
    }
}
