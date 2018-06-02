package it.menzani.bts.playerspawn.database;

import it.menzani.bts.datastore.wrapper.SQLDatabaseCallable;
import it.menzani.bts.playerspawn.Spawn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GetSpawn implements SQLDatabaseCallable {
    private final PreparedStatement preparedStatement;
    private final UUID playerId;

    public GetSpawn(PreparedStatement preparedStatement, UUID playerId) {
        this.preparedStatement = preparedStatement;
        this.playerId = playerId;
    }

    @Override
    public Spawn call(Object connection) throws SQLException {
        preparedStatement.setObject(1, playerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean validRow = resultSet.next();
        if (validRow) {
            return new Spawn(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
        }
        return null;
    }

    @Override
    public String getErrorMessage() {
        return "Could not retrieve player spawn." + System.lineSeparator() + "context: " + toString();
    }

    @Override
    public String toString() {
        return "GetSpawn{" +
                "playerId=" + playerId +
                '}';
    }
}
