package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Database {
    private static final Database database = new Database();
    private Connection connection;
    private static final String dbUrl = "jdbcURL";

    private Database() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("database_url");
            connection = DriverManager.getConnection(resourceBundle.getString(dbUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance(){
        return database;
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}