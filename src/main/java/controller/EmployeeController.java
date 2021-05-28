package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Boat;
import model.Customer;
import model.Employee;
import model.Model;
import view.BoatMenuView;
import view.EmployeeMenuView;

import javax.rmi.CORBA.ValueHandler;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeController {
    static ObjectMapper mapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);
    static Model model = new Model();

    public static void execute() throws IOException {

        outer:
        while (true) {
            int choice = EmployeeMenuView.get();
            switch (choice) {
                case 1:
                    showUsers();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    break outer;
            }


        }
    }

    private static void deleteUser() throws IOException {
        System.out.println("Please enter the Id of the user you want to delete:");
        int userId = scanner.nextInt();

        for (Employee employee : model.employees){
            if(userId == employee.getEmployeeId()){
                model.employees.remove(employee);
                System.out.println("The user with id:" + userId + " has been deleted");
                break;
            }

        }
        // Java object to JSON file
        mapper.writeValue(new File("src/main/java/model/model.json"), model);

    }

    private static void updateUser() {
        System.out.println("Please enter the id of the User ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter new password for the user");
        String newPassword = scanner.nextLine();
        for (Employee employee : model.employees){
            if(employee.getEmployeeId()==userId){
                employee.setPassword(newPassword);
                System.out.println("The user with id: " + userId + " has been updated");
                break;
            }
        }
        try {
            mapper.writeValue(new File("src/main/java/model/model.json"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addUser() {
        System.out.println("Please enter user name:");
        String keyInput_User = scanner.nextLine();
        System.out.println("Please enter new password:");
        String keyInput_Password = scanner.nextLine();
        Employee employee = new Employee(model.nextEmployeeId(), keyInput_User,  keyInput_Password);
        System.out.println("A new user is added successfully.");


        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
            model.employees.add(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Java object to JSON file
            mapper.writeValue(new File("src/main/java/model/model.json"), model);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void showUsers() {
        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Employee employee : model.employees) {
            System.out.println(employee);
        }
    }
}
