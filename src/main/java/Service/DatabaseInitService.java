package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInitService {

    public static void main(String[] args) {
        String initDBfile = "sql/init_db.sql";
        try {
            BufferedReader br = new BufferedReader(new FileReader(initDBfile));
            Connection connection = Database.getInstance().getConnection();
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                if (line.endsWith(";")) {
                    try {
                        connection.createStatement().executeUpdate(sb.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    sb.setLength(0);
                }
            }
           connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}