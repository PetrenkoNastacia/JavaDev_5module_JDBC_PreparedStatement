package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Database.Database;

public class DatabasePopulateService {
    static String populateDBfileUrl = "sql/populate_db.sql";
    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            String SQL = Files.readString(Path.of(populateDBfileUrl));
            statement.executeUpdate(SQL);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
