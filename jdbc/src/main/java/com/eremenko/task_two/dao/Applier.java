package com.eremenko.task_two.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
interface Applier {
    void apply(PreparedStatement preparedStatement) throws SQLException, IOException;
}
