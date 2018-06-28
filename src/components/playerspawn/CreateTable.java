package it.menzani.bts.components.playerspawn;

import it.menzani.bts.components.Component;
import it.menzani.bts.persistence.sql.wrapper.SQLDatabaseRunnable;

import java.sql.Connection;
import java.sql.SQLException;

class CreateTable implements SQLDatabaseRunnable {
    @Override
    public void run(Connection connection, Component component) throws SQLException {
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS " + component.getName() +
                "(playerId UUID PRIMARY KEY, spawnX INT NOT NULL, spawnY INT NOT NULL, spawnZ INT NOT NULL)");
    }

    @Override
    public String getErrorMessage() {
        return "Could not create database table.";
    }
}
