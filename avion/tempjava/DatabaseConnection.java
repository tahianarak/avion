package avion.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    static {
        // Charger la configuration à partir du fichier db.properties
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            dbUrl = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible de charger le fichier de configuration", e);
        }
    }


    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Le driver PostgreSQL n'a pas pu être chargé", e);
        }
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
