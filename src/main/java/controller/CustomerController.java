package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import model.Model;
import view.CustomerMenuView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CustomerController {
    static Model model = new Model();
    static ObjectMapper mapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);

    public static void execute() {
        outer:
        while (true) {
            int choice = CustomerMenuView.get();

            switch (choice) {
                case 1:
                    try {
                        listCustomers();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    break;
                case 4:
                    try {
                        deleteCustomer(model,  mapper, scanner);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    break outer;
            }


        }
    }

    private static void listCustomers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
//        String padded = String.format("%-20s", str);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-5s", "ID")+ String.format("%-20s", "NAME") + String.format("%-20s", "PHONE NUMBER") + String.format("%-20s", "E-MAIL") + String.format("%-20s", "ADDRESS"));
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (Customer customerIn : model.customers) {
            System.out.println(customerIn.getCustomerId() + "\t" + String.format("%-20s", customerIn.getName()) + " \t" +
                    String.format("%-10s", customerIn.getPhoneNumber()) + " \t" + String.format("%-20s", customerIn.getEmail()) + "\t" + String.format("%-20s", customerIn.getAddress()));
        }
    }

    private static void addCustomer(){
        ObjectMapper mapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter customer name:");
        String keyInput_customer = scanner.nextLine();
        System.out.println("Please enter phone number of the customer ");
        int keyInput_phoneNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter email of the customer");
        String keyInput_email = scanner.nextLine();
        System.out.println("Please enter address details of the customer");
        String keyInput_address = scanner.nextLine();
        Customer customer = new Customer(model.nextCustomerId(), keyInput_customer, keyInput_phoneNumber, keyInput_email, keyInput_address);
        System.out.println("A new customer is added successfully.");


        try {
            model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
            model.customers.add(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Java object to JSON file
            mapper.writeValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), model);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void deleteCustomer(Model model, ObjectMapper mapper,Scanner scanner) throws IOException {
        System.out.println("Please enter the Id of the customer you want to delete:");
        int customerId = scanner.nextInt();

        for (Customer customerIn : model.customers){
            if(customerId == customerIn.getCustomerId()){
                model.customers.remove(customerIn);
                System.out.println("The customer with id:" + customerId + " has been deleted");
                break;
            }

        }
        // Java object to JSON file
        mapper.writeValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), model);
    }

}
