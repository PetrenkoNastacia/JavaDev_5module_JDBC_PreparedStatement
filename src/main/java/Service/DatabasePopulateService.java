package Service;

import java.sql.*;

public class DatabasePopulateService {

    static final String clientSQL = "INSERT INTO client (name) VALUES (?,?)";
    static final String workerSQL = "INSERT INTO worker (name, birthday, level, salary) VALUES (?,?,?,?,?)";
    static final String projectSQL = "INSERT INTO project (client_ID, start_date, finish_date) VALUES (?,?,?,?)";
    static final String projectWorkerSQL = "INSERT INTO project_worker (project_ID, worker_ID) VALUES (?,?)";

    public static void main(String[] args) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        connection.setAutoCommit(false);

        //insert workers
        PreparedStatement statementWorker = connection.prepareStatement(workerSQL);

        int[] workerID = {1,2,3,4,5,6,7,8,9,10};
        String[] workerNames = {
                "Anastasiia", "Karen", "Helen", "Linda", "Phoebe", "Piper", "Prue", "David", "James", "Roman"
        };
        Date[] workerBirthdays = {
                Date.valueOf("1994-02-23"),
                Date.valueOf("1991-01-10"),
                Date.valueOf("1992-01-30"),
                Date.valueOf("1990-05-25"),
                Date.valueOf("1988-06-16"),
                Date.valueOf("1988-07-04"),
                Date.valueOf("1982-08-17"),
                Date.valueOf("1994-03-13"),
                Date.valueOf("1990-02-27"),
                Date.valueOf("1997-06-19"),
        };
        String[] workerLevel = {
                "Middle", "Middle", "Senior", "Senior", "Middle", "Middle", "Middle", "Junior", "Junior", "Trainee"
        };
        int[] workerSalary = {3500, 3000, 5200, 4100, 3000, 3000, 3000, 2000, 2000, 800};

        for (int i = 0; i < workerID.length; i++) {
            statementWorker.setInt(1, workerID[i]);
            statementWorker.setString(2, workerNames[i]);
            statementWorker.setDate(3, Date.valueOf(String.valueOf(workerBirthdays[i])));
            statementWorker.setString(4, workerLevel[i]);
            statementWorker.setInt(5, workerSalary[i]);

            statementWorker.addBatch();
        }
        statementWorker.executeBatch();
        statementWorker.close();

        //insert clients
        PreparedStatement statementClient = connection.prepareStatement(clientSQL);
        int[] clientID = {1,2,3,4,5};
        String[] clientNames = {"Hideo", "Hidetaka", "Shigeru", "John", "Will"};

        for (int i = 0; i < clientID.length; i++) {
            statementClient.setInt(1, clientID[i]);
            statementClient.setString(2, clientNames[i]);
            statementClient.addBatch();
        }
        statementClient.executeBatch();

        //insert projects
        PreparedStatement statementProject = connection.prepareStatement(projectSQL);
        int[] projectID = {1,2,3,4,5,6,7,8,9,10};
        int[] projectClientID = {1,2,3,4,5,1,2,3,4,5};
        Date[] startDates = {
                Date.valueOf("2026-06-06"),
                Date.valueOf("2024-04-14"),
                Date.valueOf("2023-11-11"),
                Date.valueOf("2025-05-05"),
                Date.valueOf("2026-06-06"),
                Date.valueOf("2024-01-19"),
                Date.valueOf("2023-08-08"),
                Date.valueOf("2026-06-06"),
                Date.valueOf("2027-07-07"),
                Date.valueOf("2024-04-04"),
                };
        Date[] finishDates = {
                Date.valueOf("2030-12-12"),
                Date.valueOf("2024-05-15"),
                Date.valueOf("2024-11-12"),
                Date.valueOf("2026-06-16"),
                Date.valueOf("2027-07-27"),
                Date.valueOf("2028-08-18"),
                Date.valueOf("2027-07-17"),
                Date.valueOf("2028-03-13"),
                Date.valueOf("2030-03-30"),
                Date.valueOf("2024-05-05"),
        };

        for (int i = 0; i < projectID.length; i++) {
            statementProject.setInt(1, projectID[i]);
            statementProject.setInt(2, projectClientID[i]);
            statementProject.setDate(3, Date.valueOf(String.valueOf(startDates[i])));
            statementProject.setDate(4, Date.valueOf(String.valueOf(finishDates[i])));

            statementProject.addBatch();
        }
        statementProject.executeBatch();

        //insert project worker
        PreparedStatement statementProjectWorker = connection.prepareStatement(projectWorkerSQL);

        int[] projectsID = {1,2,3,4,5,6,7,8,9,10};
        int[] workersID = {1,2,3,4,5,6,7,8,9,10};

        for (int i = 0; i < projectsID.length; i++) {
            statementProjectWorker.setInt(1, projectsID[i]);
            statementProjectWorker.setInt(2, workersID[i]);
            statementProjectWorker.addBatch();
        }
        statementWorker.executeBatch();

        connection.commit();
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
