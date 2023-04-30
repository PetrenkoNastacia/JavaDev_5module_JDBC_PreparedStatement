package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Database.Database;

public class DatabaseInitService {
    public static void main(String[] args) {
        String initDBfileURL = "sql/init_db.sql";
        Connection connection = Database.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            String SQL = Files.readString(Path.of(initDBfileURL));
            statement.executeUpdate(SQL);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}