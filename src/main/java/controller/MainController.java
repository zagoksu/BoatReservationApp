package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employee;
import model.Model;
import view.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MainController {
    Model model;
    ObjectMapper mapper = new ObjectMapper();

    public void execute() throws IOException, ParseException {
        // Read the model
        model = Model.readModel();
        try {

            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);

            Scanner scanner = new Scanner(System.in);
            boolean isAuthenticated = false;
            System.out.println("Welcome to Boat Reservation Application");
            while (!isAuthenticated) {
                System.out.print("Please Enter your username: ");
                String userName = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (authenticate(userName, password, model)) {
                    isAuthenticated = true;
                } else {
                    System.out.println("Incorrect username or password. Please enter your credentials again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        outer:
        while (true) {
            int choice = MainMenuView.get();

            switch (choice) {
                case 1:
                    new BoatMenuView();
                    BoatController.execute();
                    break;
                case 2:
                    new CustomerMenuView();
                    CustomerController.execute();
                    break;
                case 3:
                    new RentalMenuView();
                    RentalController.execute();
                    break;
                case 4:
                    new EmployeeMenuView();
                    EmployeeController.execute();
                    break;
                case 5:
                    break outer;
            }


        }
    }
    private static boolean authenticate (String userName, String password, Model model1){
        boolean isAuthenticated = false;
        for (Employee employee : model1.employees) {
            if (employee.getUserName().equals(userName) && employee.getPassword().equals(password)) {
                isAuthenticated = true;
            }
        }
        return isAuthenticated;

    }

}
