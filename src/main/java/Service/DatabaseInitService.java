package Service;

import java.io.IOException;
import java.sql.SQLException;

import Database.Database;

public class DatabaseInitService {

    private static final String initDBfileURL = "sql/init_db.sql";

    public static void main(String[] args) {

        SQLFileReader sqlFileReader = new SQLFileReader();

        try {
            String sql = sqlFileReader.readFile(initDBfileURL);
            Database.sendSQLExecute(sql);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}