package Service;

import selectClasses.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Database.Database;

public class DatabaseQueryService {

    private final Connection connection = Database.getInstance().getConnection();

    public List<selectClasses.FindLongestProject> findLongestProject(){
        List<FindLongestProject> longestProjects = new ArrayList<>();

        try(Statement statement = connection.createStatement()){
            String SQL = Files.readString(Path.of("sql/find_longest_project.sql"));
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()){
                FindLongestProject findLongestProject = new FindLongestProject();
                findLongestProject.setName(rs.getString("name"));
                findLongestProject.setMonthCount(rs.getInt("month_count"));
                longestProjects.add(findLongestProject);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return longestProjects;
    }

    public List<FindMaxProjectsClient> findMaxProjectsClient(){
        List<FindMaxProjectsClient> maxProjectsClients = new ArrayList<>();

        try(Statement statement = Database.getInstance().getConnection().createStatement()){
            String SQL = Files.readString(Path.of("sql/find_max_projects_client.sql"));
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                FindMaxProjectsClient findMaxProjectsClient = new FindMaxProjectsClient();
                findMaxProjectsClient.setName(rs.getString("name"));
                findMaxProjectsClient.setCount(rs.getInt("project_count"));
                maxProjectsClients.add(findMaxProjectsClient);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return maxProjectsClients;
    }

    public List<FindMaxSalaryWorker> findMaxSalaryWorkers() {
        List<FindMaxSalaryWorker> workers = new ArrayList<>();

        try(Statement statement = Database.getInstance().getConnection().createStatement()){
            String SQL = Files.readString(Path.of("sql/find_max_salary_worker.sql"));
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()){
               FindMaxSalaryWorker findMaxSalaryWorker = new FindMaxSalaryWorker();
               findMaxSalaryWorker.setName(rs.getString("name"));
               findMaxSalaryWorker.setSalary(rs.getInt("salary"));
               workers.add(findMaxSalaryWorker);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return workers;
    }

    public List<FindYoungestEldestWorker> findYoungestEldestWorkers(){
        List<FindYoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();

        try(Statement statement = Database.getInstance().getConnection().createStatement()) {
            String SQL = Files.readString(Path.of("sql/find_youngest_eldest_workers.sql"));
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()){
                FindYoungestEldestWorker findYoungestEldestWorker = new FindYoungestEldestWorker();
                findYoungestEldestWorker.setType(rs.getString("type"));
                findYoungestEldestWorker.setName(rs.getString("name"));
                findYoungestEldestWorker.setBirthday(rs.getString("birthday"));
                youngestEldestWorkers.add(findYoungestEldestWorker);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return youngestEldestWorkers;
    }

    public List<PrintProjectPrices> printProjectPrices() throws IOException {
        List<PrintProjectPrices> projectPrices = new ArrayList<>();
        String SQL = Files.readString(Path.of("sql/print_project_prices.sql"));
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                PrintProjectPrices printProjectPrices = new PrintProjectPrices();
                printProjectPrices.setName(rs.getString("name"));
                printProjectPrices.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectPrices;
    }
}