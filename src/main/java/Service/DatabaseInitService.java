package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {

    public static void main(String[] args) {

        final String initDBfile = "sql/init_db.sql";
        Connection connection = Database.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(initDBfile));
            statement.executeUpdate(sql);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}