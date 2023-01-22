package org.example.dao.daoimpl;

import org.example.config.Util;
import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Job;

import java.sql.*;
import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection = Util.getConnection();
    @Override
    public void createEmployee() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("""
create  table  employees(
       id serial primary key ,
       first_name varchar,
       last_name varchar,
       age int,
       email varchar unique ,
       job_id int references jobs(id));""");
            System.out.println("Successfully create table!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
insert into employees(first_name,last_name,age,email,job_id)values (?,?,?,?,?);""")){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLatsName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully add employee!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("""
drop table employees""");
            System.out.println("Successfully drop table!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cleanTable() {
        System.out.println("""
                1- If you want to truncate click ==>1
                2- If you want to delete clik ==>2""");
        Scanner scanner = new Scanner(System.in);
        int comand = scanner.nextInt();
        if (comand == 1) {
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery("""
truncate table employees;""");
                System.out.println("Successfully truncate table!");
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            }else if (comand == 2) {
            try(Statement statement = connection.createStatement()){
                statement.executeQuery("""
delete from emloyees;""");
                System.out.println("Successfully delete table!");
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

        }

    }
    @Override
    public void updateEmployee(Long id, Employee employee) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("""
        update employees set first_name = ?, last_name = ? , age = ? ,email =? , job_id=? where id =?;""")){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLatsName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.setLong(6,id);
            preparedStatement.executeUpdate();
            System.out.println("Successfully ubdate table!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            statement.executeQuery("""
select * from employees;""");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLatsName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
                employeeList.add(employee);
            }resultSet.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeList;
    }

    @Override
    public Employee findByEmail(String email) {
                Employee employee = new Employee();
        try (PreparedStatement preparedStatement = connection.prepareStatement("""
select * from employees where email = ?;""")){
            preparedStatement.setString(1,"email");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLatsName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employee ;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job> jobMap = new LinkedHashMap<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("""
         select * from employees join jobs j on employees.job_id = j.id  where employees.id = ?;""")){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLatsName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperince(resultSet.getInt("experince"));
                jobMap.put(employee,job);
            }resultSet.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return jobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employeeList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("""
select * from  jobs join employees e on e.id = job.id  where jobs.position = ?;""")){
            preparedStatement.setString(1,position);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLatsName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
                employeeList.add(employee);
            }resultSet.close();


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeList;
    }
}
