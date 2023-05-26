package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SQLFileReader {

    public String readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }

    public List<String> readDataForClientTable(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        List<String> clientList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.equals("INSERT INTO client")) {
                br.readLine();
                br.readLine();
                while (!line.contains(";")) {
                    line = br.readLine();
                    String clientName = line.replaceAll("[^A-Za-z0-9]", "");
                    clientList.add(clientName);
                }
            }
        }
        br.close();

        return clientList;
    }

    public List<String> readDataForWorkerTable(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        List<String> workerDataList = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (line.equals("INSERT INTO worker")) {
                line = br.readLine();
                while (!line.contains(";")) {
                    line = br.readLine();
                    String workerData = line.replaceAll("[A-Za-z0-9]", "");
                    workerDataList.add(workerData);
                }
            }
        }
        br.close();

        return workerDataList;
    }

    public List<String> readDataForProjectWorkerTable(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        List<String> projectWorkerList = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (line.equals("INSERT INTO project_worker")) {
                line = br.readLine();
                while (!line.contains(";")) {
                    line = br.readLine();
                    String projectWorkerData = line.replaceAll("[^0-9]", "");
                    projectWorkerList.add(projectWorkerData);
                }
            }
        }
        br.close();

        return projectWorkerList;
    }

    public List<String> readDataForProjectTable(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        List<String> workerDataList = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (line.equals("INSERT INTO project")) {
                line = br.readLine();
                while (!line.contains(";")) {
                    line = br.readLine();
                    String workerData = line.replaceAll("[^A-Za-z0-9]", "");
                    workerDataList.add(workerData);
                }
            }
        }
        br.close();
        return workerDataList;
    }
}
