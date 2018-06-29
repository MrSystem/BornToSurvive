package it.menzani.bts.configuration;

import it.menzani.bts.BornToSurvive;
import it.menzani.bts.persistence.DatabaseCredentials;
import it.menzani.logger.api.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfiguration {
    private final FileConfiguration config;
    private final Logger logger;

    public MainConfiguration(BornToSurvive bornToSurvive) {
        bornToSurvive.saveDefaultConfig();

        config = bornToSurvive.getConfig();
        config.setDefaults(new MemoryConfiguration()); // Do not use embedded config.yml as a default.
        logger = bornToSurvive.getRootLogger();
    }

    private DatabaseCredentials databaseCredentials;

    public DatabaseCredentials getDatabaseCredentials() {
        return databaseCredentials;
    }

    public boolean validate() {
        Validation validation = new Validation();
        databaseCredentials = validateDatabase(validation);

        if (validation.isSuccessful()) return false;
        logger.fatal("config.yml - " + validation);
        return true;
    }

    private DatabaseCredentials validateDatabase(Validation validation) {
        ConfigurationSection database = config.getConfigurationSection("database");
        String host = database.getString("host");
        String name = database.getString("name");
        String user = database.getString("user");
        String password = database.getString("password");
        if (host == null || host.isEmpty()) {
            validation.addProblem(database, "host", SimpleProblem.NULL_OR_EMPTY);
        }
        if (name == null || name.isEmpty()) {
            validation.addProblem(database, "name", SimpleProblem.NULL_OR_EMPTY);
        }
        if (user == null || user.isEmpty()) {
            validation.addProblem(database, "user", SimpleProblem.NULL_OR_EMPTY);
        }
        if (password == null || password.isEmpty()) {
            validation.addProblem(database, "password", SimpleProblem.NULL_OR_EMPTY);
        }
        return new DatabaseCredentials(host, name, user, password);
    }
}
