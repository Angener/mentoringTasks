package reflection_api_usage.controller;

import reflection_api_usage.Runner;
import reflection_api_usage.entity.Appliance;
import reflection_api_usage.entity.ElectricEquipment;
import reflection_api_usage.entity.Hoover;
import reflection_api_usage.view.Printer;

import java.util.HashMap;
import java.util.Map;

public final class Executor {
    private int keyCounter = 0;
    private String[] allAppClasses = {
            ClassCreator.class.getName(),
            ClassScanner.class.getName(),
            FieldGetter.class.getName(),
            FieldsSetter.class.getName(),
            MethodInvoker.class.getName(),
            PropChecker.class.getName(),
            Appliance.class.getName(),
            ElectricEquipment.class.getName(),
            Hoover.class.getName(),
            Printer.class.getName(),
            Executor.class.getName(),
            Runner.class.getName()
    };
    private Map<Integer, Object> equipment = new HashMap<>();
    private ClassCreator classCreator = new ClassCreator();

    private int getKeyCounter() {
        keyCounter++;
        return keyCounter;
    }

    public Map<Integer, Object> getEquipment() {
        return equipment;
    }

    public void execute() {
        getMetadataAllAppClasses(allAppClasses);
        setEquipmentMapViaReflectionApi();
    }

    private void getMetadataAllAppClasses(String[] allAppClasses) {
        for (String s : allAppClasses) {
            new Printer<String>().print("Class ================>" + s + "<================");
            new ClassScanner(s);
        }
    }

    private void setEquipmentMapViaReflectionApi() {
        equipment.put(getKeyCounter(), classCreator.create(Appliance.class.getName()));
        equipment.put(getKeyCounter(), classCreator.createAppliance(2.2f, 12));
        equipment.put(getKeyCounter(), classCreator.create(Hoover.class.getName()));
        equipment.put(getKeyCounter(), classCreator.createHoover(1.8f, 220,
                "A50", "Bosch", "Russia"));
    }
}
