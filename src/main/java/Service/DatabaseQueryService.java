package Service;

import selectClasses.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static void main(String[] args) {
        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
        System.out.println(databaseQueryService.findLongestProjects());
        System.out.println(databaseQueryService.findMaxProjectsClients());
        System.out.println(databaseQueryService.findMaxSalaryWorkers());
        System.out.println(databaseQueryService.findYoungestEldestWorkers());
        System.out.println(databaseQueryService.printProjectPrices());
    }

    public List<FindLongestProject> findLongestProjects(){
        List<FindLongestProject> longestProjects = new ArrayList<>();

        String sql = SQLFileReader.readFile("sql/find_longest_project.sql");

        try(Statement statement = Database.getInstance().getConnection().createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                longestProjects.add(FindLongestProject.builder()
                        .name(rs.getString("NAME"))
                        .monthCount(rs.getInt("MONTH_COUNT"))
                        .build());
                statement.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return longestProjects;
    }

    public List<FindMaxProjectsClient> findMaxProjectsClients(){
        List<FindMaxProjectsClient> maxProjectsClients = new ArrayList<>();

        String sql = SQLFileReader.readFile("sql/find_max_projects_client.sql");
        try(Statement statement = Database.getInstance().getConnection().createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                maxProjectsClients.add(FindMaxProjectsClient.builder()
                        .name(rs.getString("NAME"))
                        .count(rs.getInt("PROJECT_COUNT"))
                        .build());
                statement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return maxProjectsClients;
    }

    public List<FindMaxSalaryWorker> findMaxSalaryWorkers(){
        List<FindMaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();

        String sql = SQLFileReader.readFile("sql/find_max_salary_worker.sql");

        try(Statement statement = Database.getInstance().getConnection().createStatement()){
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                maxSalaryWorkers.add(FindMaxSalaryWorker.builder()
                        .name(rs.getString("NAME"))
                        .salary(rs.getInt("SALARY"))
                        .build());
                statement.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return maxSalaryWorkers;
    }

    public List<FindYoungestEldestWorker> findYoungestEldestWorkers(){
        List<FindYoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();

        String sql = SQLFileReader.readFile("sql/find_youngest_eldest_workers.sql");
        try(Statement statement = Database.getInstance().getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                youngestEldestWorkers.add(FindYoungestEldestWorker.builder()
                        .type(rs.getString("TYPE"))
                        .name(rs.getString("NAME"))
                        .birthday(LocalDate.parse(rs.getString("BIRTHDAY")))
                        .build());
                statement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return youngestEldestWorkers;
    }

    public List<PrintProjectPrices> printProjectPrices(){
        List<PrintProjectPrices> projectPrices = new ArrayList<>();

        String sql = SQLFileReader.readFile("sql/print_project_prices.sql");
        try(Statement statement = Database.getInstance().getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                projectPrices.add(PrintProjectPrices.builder()
                        .id(rs.getString("NAME"))
                        .price(rs.getInt("PRICE"))
                        .build());
                statement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return projectPrices;
    }
}