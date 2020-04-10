package functional_interface_sandbox;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class CustomTimer {
    private int SECONDS;
    private final int PAUSE_OF_MILLS_BETWEEN_SECONDS = 1000;

    private BiPredicate<Integer, Integer> isLastSecond = Integer::equals;
    private Consumer<String> print = System.out::println;
    private Function<Integer, String> count = i -> i + " Mississippi,";
    private BiFunction<
            Integer,
            Function<Integer, String>,
            String
            > countLast = (i, f) ->
            f.apply(i).replaceAll(",", "") + ".";

    private Runnable runnable = () -> {
        for (int i = 1; i <= SECONDS; i++) {
            countSecond(i);
            sleepThread();
        }
    };

    public CustomTimer(int seconds) {
        this.SECONDS = seconds;
        execute();
    }

    private void execute() {
        runnable.run();
        print.accept(
                Messenger.getDefaultAlert()
        );

        print.accept(
                new Messenger() {
                    @Override
                    public String getMessage() {
                        return "";
                    }
                }.reportTimeIsUp()
        );

        alert(() -> "Your dish is ready!");
    }

    private void countSecond(int second) {
        if (isLastSecond.test(second, SECONDS)) {
            print.accept(countLast.apply(second, count));
        } else {
            print.accept(count.apply(second));
        }
    }

    private void sleepThread() {
        try {
            Thread.sleep(PAUSE_OF_MILLS_BETWEEN_SECONDS);
        } catch (InterruptedException e) {
            System.err.println("The thread was interrupt");
        }
    }

    private void alert(Messenger msn) {
        print.accept(msn.getMessage());
    }
}
