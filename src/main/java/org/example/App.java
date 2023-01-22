package org.example;

import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;
import org.example.service.JobService;
import org.example.service.serviceimpl.EmployeeServiceImpl;
import org.example.service.serviceimpl.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();
        while (true) {
            System.out.println("""
                    $$$$$$$$$$$$$$$$$$$$   Employee    $$$$$$$$$$$$$$$$$$$$$$
                    $                1-Create Employee Table ==>1           $
                    $                2-Add Employee ==>2                    $
                    $                3-Drop Table ==>3                      $
                    $                4-Clean Table ==>4                     $
                    $                5-Update Employee ==>5                 $
                    $                6-Get All Employees ==>6                $
                    $                7-Find By email ==>7                   $
                    $                8-Get Employee By Id ==>8              $
                    $                9-Get Employye By Position ==>9        $
                    $$$$$$$$$$$$$$$$$$$$$      Job      $$$$$$$$$$$$$$$$$$$$$
                    $                10-Create Job Table ==>10              $
                    $                11-Add Job ==>11                       $
                    $                12-Get Job Id ==>12                    $
                    $                13-Sort By Experience ==>13            $
                    $                14-Get Job By Employee Id ==>14        $                             
                    $                15-Get Description Column ==>15        $
                    $                16-Drop table job ==>16                $            
                    $$$$$$$$$$$$$$$$     0 - Exit ==> 0    $$$$$$$$$$$$$$$$$$  """);
            int comand = scanner.nextInt();
            switch (comand){
                case 1:
                    employeeService.createEmployee();
                    break;
                case 2:
                    System.out.println("Write first name :");
                    String firstName = new Scanner(System.in).nextLine();
                    System.out.println("Write last name :");
                    String lastName = new Scanner(System.in).nextLine();
                    System.out.println("Write age :");
                    int age = new Scanner(System.in).nextInt();
                    System.out.println("Write email:");
                    String email = new Scanner(System.in).nextLine();
                    System.out.println("Write job id :");
                    int jobId = new Scanner(System.in).nextInt();
                    employeeService.addEmployee(new Employee(firstName,lastName,age,email,jobId));
                    break;
                case 3:
                    employeeService.dropTable();
                    break;
                case 4:
                    employeeService.cleanTable();
                    break;
                case 5:
                    System.out.println("Write id ");
                    long id = new Scanner(System.in).nextLong();
                    System.out.println("Write first name :");
                    String firstName1 = new Scanner(System.in).nextLine();
                    System.out.println("Write last name :");
                    String lastName1 = new Scanner(System.in).nextLine();
                    System.out.println("Write age :");
                    int age1 = new Scanner(System.in).nextInt();
                    System.out.println("Write email:");
                    String email1 = new Scanner(System.in).nextLine();
                    System.out.println("Write job id :");
                    int jobId1 = new Scanner(System.in).nextInt();
                    employeeService.updateEmployee(id,new Employee(firstName1,lastName1,age1,email1,jobId1));
                    break;
                case 6:
                    System.out.println(employeeService.getAllEmployees());
                    break;
                case 7:
                    String email3 = new Scanner(System.in).nextLine();
                    System.out.println(employeeService.findByEmail(email3));
                    break;
                case 8:
                    System.out.println("Write employee id:");
                    Long eid = new Scanner(System.in).nextLong();
                    System.out.println(employeeService.getEmployeeById(eid));
                    break;
                case 9:
                    System.out.println("Write employee position:");
                    String position = new Scanner(System.in).nextLine();
                    System.out.println(employeeService.getEmployeeByPosition(position));
                    break;
                case 10:
                    jobService.createJobTable();
                    break;
                case 11:
                    System.out.println("Write position :");
                    String position1 = new Scanner(System.in).nextLine();
                    System.out.println("Write profession :");
                    String profession =  new Scanner(System.in).nextLine();
                    System.out.println("Write descripton :");
                    String description =  new Scanner(System.in).nextLine();
                    System.out.println("Write experince :");
                    int experince =  new Scanner(System.in).nextInt();
                    jobService.addJob(new Job(position1,profession,description,experince));
                    break;
                case 12:
                    System.out.println("Write job id :");
                    long jobid =  new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobById(jobid));
                    break;
                case 13:
                    System.out.println("Sort by experince asc ili desc? ");
                    String sort =  new Scanner(System.in).nextLine();
                    if (sort.equalsIgnoreCase("Asc")){
                        System.out.println(jobService.sortByExperience(sort));
                    } else if (sort.equalsIgnoreCase("Desc")) {
                        System.out.println(jobService.sortByExperience(sort));
                    }break;
                case 14:
                    System.out.println("Write empploye id :");
                    Long employeeId =  new Scanner(System.in).nextLong();
                    System.out.println(jobService.getJobByEmployeeId(employeeId));
                    break;
                case 15:
                    jobService.deleteDescriptionColumn();
                    break;
                case 16:
                    jobService.drobTable();
                    break;
                case 0:
                    return;

            }
        }
    }
}
