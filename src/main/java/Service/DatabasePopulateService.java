package Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabasePopulateService {
    public static void main(String[] args) throws SQLException, IOException {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.insertWorkers();
        databasePopulateService.insertClients();
        databasePopulateService.insertProjects();
        databasePopulateService.insertProjectWorkers();
    }
    private static final String clientSQL = "INSERT INTO client (id, name) VALUES (?,?)";
    private static final String workerSQL = "INSERT INTO worker (id, name, birthday, level, salary) VALUES (?,?,?,?,?)";
    private static final String projectSQL = "INSERT INTO project (id, client_ID, start_date, finish_date) VALUES (?,?,?,?)";
    private static final String projectWorkerSQL = "INSERT INTO project_worker (project_ID, worker_ID) VALUES (?,?)";
    private final Connection connection = Database.getInstance().getConnection();

    private void insertWorkers() throws IOException, SQLException {
        PreparedStatement statementWorker = connection.prepareStatement(workerSQL);

        FileReader fr = new FileReader("src/main/java/Worker");
        BufferedReader br = new BufferedReader(fr);

        int workerID = 1;
        while (br.ready()) {
            String line = br.readLine();
            String[] values = line.split(",");

            statementWorker.setString(1, "" + workerID++);
            statementWorker.setString(2, values[0]);
            statementWorker.setDate(3, Date.valueOf(values[1]));
            statementWorker.setString(4, values[2]);
            statementWorker.setInt(5, Integer.parseInt(values[3]));

            statementWorker.addBatch();
        }
        statementWorker.executeBatch();
    }

    private void insertClients() throws IOException, SQLException {
        PreparedStatement statementClient = connection.prepareStatement(clientSQL);

        FileReader fr = new FileReader("src/main/java/Client");
        BufferedReader br = new BufferedReader(fr);

        int clientID = 1;
        while (br.ready()) {
            String line = br.readLine();
            String[] values = line.split(",");
            statementClient.setInt(1, clientID++);
            statementClient.setString(2, values[1]);

            statementClient.addBatch();
        }
        statementClient.executeBatch();
    }

    private void insertProjects() throws IOException, SQLException {
        PreparedStatement statementProject = connection.prepareStatement(projectSQL);

        FileReader fr = new FileReader("src/main/java/Project");
        BufferedReader br = new BufferedReader(fr);

        int projectID = 1;
        while (br.ready()) {
            String line = br.readLine();
            String[] values = line.split(",");
            statementProject.setInt(1, projectID++);
            statementProject.setInt(2, Integer.parseInt(values[1]));
            statementProject.setDate(3, Date.valueOf(values[2]));
            statementProject.setDate(4, Date.valueOf(values[3]));

            statementProject.addBatch();
        }
        statementProject.executeBatch();
    }

    private void insertProjectWorkers() throws IOException, SQLException {
        PreparedStatement statementProjectWorker = connection.prepareStatement(projectWorkerSQL);

        FileReader fr = new FileReader("src/main/java/ProjectWorker");
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            String line = br.readLine();
            String[] values = line.split(",");
            statementProjectWorker.setInt(1, Integer.parseInt(values[0]));
            statementProjectWorker.setInt(2, Integer.parseInt(values[1]));

            statementProjectWorker.addBatch();
        }
        statementProjectWorker.executeBatch();
    }
}
