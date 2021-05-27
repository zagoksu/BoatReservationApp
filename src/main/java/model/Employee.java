package model;

import java.util.Random;

public class Employee {
    private Random random = new Random();
    private int employeeId;
    private String userName;
    private String password;

    public Employee( String userName, String password) {
        this.employeeId = random.nextInt(50);
        this.userName = userName;
        this.password = password;
    }

    public Employee(){

    }

    public int getEmployeeId() {
        return employeeId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
