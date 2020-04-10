package reflection_api_usage;

import reflection_api_usage.controller.Executor;
import reflection_api_usage.controller.FieldGetter;
import reflection_api_usage.controller.FieldsSetter;
import reflection_api_usage.controller.MethodInvoker;
import reflection_api_usage.view.Printer;

import java.util.Map;

public class Runner {
    private static String demarcationLine = "====================================================";

    public static void main(String[] args) {
        Executor executor = new Executor();
        executor.execute();
        new Printer<String>().print(demarcationLine);
        Map<Integer, Object> equipment = executor.getEquipment();

        FieldsSetter.setString(equipment.get(1), "function", "Cleaning");
        FieldsSetter.setString(equipment.get(1), "category", "Hoovers");

        Printer<String> printer = new Printer<>();
        printer.print(FieldGetter.getString(equipment.get(1), "function"));
        printer.print(FieldGetter.getString(equipment.get(1), "category"));
        printer.print(MethodInvoker.invokeMethod(equipment.get(1), "getCategory"));
    }
}
