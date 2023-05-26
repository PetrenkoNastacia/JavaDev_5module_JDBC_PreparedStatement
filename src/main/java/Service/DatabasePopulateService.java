package Service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.Database;

public class DatabasePopulateService {
    private static final String populateDBfileUrl = "sql/populate_db.sql";
    public static void main(String[] args) throws SQLException, IOException {
        Database database = Database.getInstance();
        Connection connection = database.getConnection();

        PreparedStatement statementClient = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
        List<String> clientTableData = new SQLFileReader().readDataForClientTable(populateDBfileUrl);

        for (String name: clientTableData) {
            statementClient.setString(1, name);
            statementClient.addBatch();
        }
        statementClient.executeBatch();
        statementClient.close();


        PreparedStatement statementWorker = connection
                .prepareStatement("INSERT INTO worker (name, birthday, level, salary) VALUES (?,?,?,?)");
        List<String> workerTableData = new SQLFileReader().readDataForWorkerTable(populateDBfileUrl);
        List<String> workerLine = new ArrayList<>();

        for (int i = 0; i < workerTableData.size(); i++) {
            workerLine.add(workerTableData.get(i));

            String[] array = workerLine.get(i).split("\\s+,");

            String workerName = array[0].trim();
            String workerBirthday = array[1].trim();
            String workerLevel = array[2].trim();
            String workerSalary = array[3].trim();

            statementWorker.setString(1,workerName);
            statementWorker.setDate(2, Date.valueOf(workerBirthday));
            statementWorker.setString(3,workerLevel);
            statementWorker.setInt(4, Integer.parseInt(workerSalary));
            statementWorker.addBatch();
            statementWorker.executeBatch();
        }
        statementWorker.close();


        PreparedStatement statementProject = connection
                .prepareStatement("INSERT INTO project (client_ID, start_date, finish_date) VALUES (?,?,?)");
        List<String> projectTableData = new SQLFileReader().readDataForProjectTable(populateDBfileUrl);
        List<String> projectLine = new ArrayList<>();

        for (int i = 0; i < projectTableData.size(); i++) {
            projectLine.add(projectTableData.get(i));

            String[] projectArray = projectLine.get(i).split(",");
            String clientID = projectArray[0].trim();
            String startDate = projectArray[1].trim();
            String finishDate = projectArray[2].trim();

            statementProject.setInt(1, Integer.parseInt(clientID));
            statementProject.setDate(2, Date.valueOf(startDate));
            statementProject.setDate(3, Date.valueOf(finishDate));
            statementProject.addBatch();
            statementProject.executeBatch();
        }
        statementProject.close();


        PreparedStatement statementPW = connection
                .prepareStatement("INSERT INTO project_worker (project_ID, worker_ID) VALUES (?,?)");
        List<String> projectWorkerTableData = new SQLFileReader().readDataForProjectWorkerTable(populateDBfileUrl);
        List<String> projectWorkerLine = new ArrayList<>();

        for (int i = 0; i < projectWorkerTableData.size(); i++){
            projectWorkerLine.add(projectWorkerTableData.get(i));

            String[] projectWorkerArray = projectWorkerLine.get(i).split("\\s+");
            String projectID = projectWorkerArray[0].trim();
            String workerID = projectWorkerArray[1].trim();

            statementPW.setInt(1, Integer.parseInt(projectID));
            statementPW.setInt(2, Integer.parseInt(workerID));

            statementPW.addBatch();
            statementPW.executeBatch();
        }
        statementPW.close();
        connection.close();
    }
}
