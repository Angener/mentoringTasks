package com.epam.eremenko.serializable_demo.dao;

import com.epam.eremenko.serializable_demo.entity.Appliances;
import com.epam.eremenko.serializable_demo.entity.ElectricEquipment;
import com.epam.eremenko.serializable_demo.entity.Hoover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SerializationTest {
    private DatabaseAccessor databaseAccessor;

    @BeforeEach
    public void setup() {
        databaseAccessor =
                DatabaseAccessorFactory.getInstance().getDatabaseAccessor();
    }

    @Test
    public void updateMethodTrowsDaoException(){
        ElectricEquipment hoover = new Hoover();
        assertThrows(DaoException.class, () ->
                databaseAccessor.update(null, "hoover.ser"));
        assertThrows(DaoException.class, () ->
                databaseAccessor.update(hoover, null));
        assertThrows(DaoException.class, () ->
                databaseAccessor.update(hoover, "dawd"));
        assertThrows(DaoException.class, () ->
                databaseAccessor.update(hoover, ".d"));
    }

    @Test
    public void serializationTest() throws DaoException {
        ElectricEquipment appliance = new Appliances(2.1f, 110);
        ElectricEquipment hoover = new Hoover(5.6f, 220);
        databaseAccessor.update(new Appliances(2.1f, 110), "appliance.ser");
        databaseAccessor.update(new Hoover(5.6f, 220,
                "hoover", "Vityaz", "Belarus"), "hoover.ser");
        assertEquals(appliance, databaseAccessor.get("appliance.ser"));
        assertEquals(appliance, databaseAccessor.get("hoover.ser"));
    }

    @Test
    public void nonSerializableFieldsTest() throws DaoException{
        databaseAccessor.update(new Appliances(2.1f, 110), "appliance.ser");
        assertEquals(0, databaseAccessor.get("appliance.ser").getVoltage());
        assertEquals(0, databaseAccessor.get("appliance.ser").getWeigh());
    }







}
