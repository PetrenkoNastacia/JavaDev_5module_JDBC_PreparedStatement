package Service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final Database database = new Database();
    private Connection connection;
//    private static final String dbUrl = "jdbcURL";

    private Database() {
        try {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("database_url");
            connection = DriverManager.getConnection("jdbc:h2:./JavaDev_4module_JDBC");
            //resourceBundle.getString(dbUrl));
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
}