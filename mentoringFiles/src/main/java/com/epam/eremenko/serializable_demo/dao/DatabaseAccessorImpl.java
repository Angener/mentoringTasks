package com.epam.eremenko.serializable_demo.dao;

import com.epam.eremenko.serializable_demo.entity.ElectricEquipment;

import java.io.*;

public class DatabaseAccessorImpl implements DatabaseAccessor {
    private final String sp = File.separator;
    private final String path = "target" + sp + "save" + sp;

    @Override
    public synchronized void update(ElectricEquipment equipment, String filename) throws DaoException {
        if (equipment == null || filename == null
                || filename.length() < 3 || !filename.contains(".")) {
            throw new DaoException("Storage not exists");
        } else {
            serialize(equipment, filename);
        }
    }

    private void serialize(ElectricEquipment equipment, String filename) throws DaoException {
        try (OutputStream outputStream = new FileOutputStream(path + filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(equipment);
        } catch (IOException ex) {
            throw new DaoException("Access denied: " + ex.getMessage());
        }
    }

    @Override
    public ElectricEquipment get(String filename) throws DaoException {
        ElectricEquipment electricEquipment;
        try (InputStream fileInputStream = new FileInputStream(path + filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            electricEquipment = (ElectricEquipment) objectInputStream.readObject();
        } catch (IOException ex) {
            throw new DaoException("Access denied: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new DaoException("Class not found: " + ex.getMessage());
        }
        return electricEquipment;
    }
}
