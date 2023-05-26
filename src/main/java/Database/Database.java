package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Database {
    private static final Database database = new Database();
    private static Connection connection;
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

    public static void sendSQLExecute(String sql) throws SQLException {

        Database database = Database.getInstance();
        connection = database.getConnection();

        Statement statement = connection.createStatement();
        statement.execute(sql);

        statement.close();
        connection.close();
    }
}