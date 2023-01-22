package org.example.dao.daoimpl;

import org.example.config.Util;
import org.example.dao.JobDao;
import org.example.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    Connection connection = Util.getConnection();

    @Override
    public void createJobTable() {
       try (Statement statement = connection.createStatement()){
           statement.executeUpdate("""
                create table jobs(
                id serial primary key ,
                position varchar,
                profession varchar,
                description varchar,
                experince int  );
                """);
           System.out.println("Successfully create table!");
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }
    @Override
    public void addJob(Job job) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(""" 
           insert into jobs(position,profession,description,experince)
           values (?,?,?,?)""")){
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2,job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4,job.getExperince());
            preparedStatement.executeUpdate();
            System.out.println("Successfully add table!");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Job getJobById(Long jobId) {
                Job job = new Job();
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
select * from jobs where id = ?;""")){
            preparedStatement.setLong(1,jobId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
           job.setId(resultSet.getLong("id"));
           job.setPosition(resultSet.getString("position"));
           job.setProfession(resultSet.getString("profession"));
           job.setDescription(resultSet.getString("description"));
           job.setExperince(resultSet.getInt("experince"));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }


    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        switch (ascOrDesc){
            case "asc":
                try(PreparedStatement preparedStatement = connection.prepareStatement("""
select * from jobs order by experince;""")){
                    preparedStatement.executeQuery();
                    ResultSet resultSet = preparedStatement.getResultSet();
                    while (resultSet.next()){
                        Job job = new Job();
                        job.setId(resultSet.getLong("id"));
                        job.setPosition(resultSet.getString("position"));
                        job.setProfession(resultSet.getString("profession"));
                        job.setDescription(resultSet.getString("description"));
                        job.setExperince(resultSet.getInt("experince"));
                        jobs.add(job);
                    }
                        resultSet.close();


        }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                break;
            case "desc":
                try(PreparedStatement preparedStatement = connection.prepareStatement("""
select * from jobs order by experince desc ;""")){
                    preparedStatement.executeQuery();
                    ResultSet resultSet = preparedStatement.getResultSet();
                    while (resultSet.next()){
                        Job job = new Job();
                        job.setId(resultSet.getLong("id"));
                        job.setPosition(resultSet.getString("position"));
                        job.setProfession(resultSet.getString("profession"));
                        job.setDescription(resultSet.getString("description"));
                        job.setExperince(resultSet.getInt("experince"));
                        jobs.add(job);
                    }
                    resultSet.close();

                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }

        }
        return jobs;
    }
    @Override
    public Job getJobByEmployeeId(Long employeeId) {
               Job job = new Job();
       try(PreparedStatement preparedStatement = connection.prepareStatement("""
select * from employees join jobs j on j.id = employees.job_id where employees.id=?;""")){
           preparedStatement.setLong(1,employeeId);
           preparedStatement.executeQuery();
           ResultSet resultSet = preparedStatement.getResultSet();
           while (resultSet.next()){
               job.setId(resultSet.getLong("id"));
               job.setPosition(resultSet.getString("position"));
               job.setProfession(resultSet.getString("profession"));
               job.setDescription(resultSet.getString("description"));
               job.setExperince(resultSet.getInt("experince"));

           }resultSet.close();

       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
        return job ;
    }

    @Override
    public void deleteDescriptionColumn() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
alter table jobs drop column description""");
            System.out.println("Successfully deleted column !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("""
drop  table jobs;""");
            System.out.println("Successfully drop table!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}

