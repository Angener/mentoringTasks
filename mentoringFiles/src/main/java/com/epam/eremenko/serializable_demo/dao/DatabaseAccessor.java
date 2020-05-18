package com.epam.eremenko.serializable_demo.dao;

import com.epam.eremenko.serializable_demo.entity.ElectricEquipment;


public interface DatabaseAccessor {
    void update(ElectricEquipment electricEquipment, String filename) throws DaoException;

    ElectricEquipment get(String filename) throws DaoException;
}
