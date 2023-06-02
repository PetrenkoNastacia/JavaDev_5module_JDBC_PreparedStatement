package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SQLFileReader {

    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.ready()) {
                sb.append(br.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

