package classic_annotation;

public final class Util {

    private Util() {
    }

    public static String getParametrizedVehicle(Vehicle vehicle, CarCheckerInterface cci) {
        if (cci.checkCar(vehicle)) {
            return vehicle.toString();
        } else {
            return "Vehicle does't much";
        }
    }
}
