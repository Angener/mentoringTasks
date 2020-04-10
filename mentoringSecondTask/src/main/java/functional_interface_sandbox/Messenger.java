package functional_interface_sandbox;

@FunctionalInterface
public interface Messenger {

    String getMessage();

    default String reportTimeIsUp() {
        return "Time's up!";
    }

    static String getDefaultAlert() {
        return "Knock-knock-knock!!!";
    }
}
