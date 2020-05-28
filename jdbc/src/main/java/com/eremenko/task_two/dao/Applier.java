package com.eremenko.task_two.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
interface Applier {
    void apply(ResultSet resultset) throws SQLException, IOException;
}
