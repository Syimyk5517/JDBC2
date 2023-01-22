package org.example.model;

public class Employee {
    private Long id;
    private String firstName;
    private String latsName;
    private  int age;
    private  String email;
    private int jobId;

    public Employee(Long id, String firstName, String latsName, int age, String email, int jobId) {
        this.id = id;
        this.firstName = firstName;
        this.latsName = latsName;
        this.age = age;
        this.email = email;
        this.jobId = jobId;
    }

    public Employee(String firstName, String latsName, int age, String email, int jobId) {
        this.firstName = firstName;
        this.latsName = latsName;
        this.age = age;
        this.email = email;
        this.jobId = jobId;
    }

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatsName() {
        return latsName;
    }

    public void setLatsName(String latsName) {
        this.latsName = latsName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return """
                 ~~~~~~   Employee  ~~~~~""" +
                "\nid: " + id +
                "\nfirstName: " + firstName +
                "\nlatsName: " + latsName + '\'' +
                "\nage: " + age +
                "\nemail: " + email +
                "\njobId: " + jobId ;
    }
}
