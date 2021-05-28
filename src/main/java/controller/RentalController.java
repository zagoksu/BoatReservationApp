package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import view.RentalMenuView;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RentalController {
    RentalList rentals;
    static Model model = new Model();
    static ObjectMapper mapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);

    public static void execute() {

        outer:
        while (true) {
            int choice = RentalMenuView.get();

            switch (choice) {
                case 1:
                    showRentals();
                    break;
                case 2:
                    addRentals();
                    break;
                case 3:
                    break;
                case 4:
                    break outer;
            }


        }
    }

    public static void addRentals() {
        Scanner scanner = new Scanner(System.in);
        try {
            model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Please select customer:");
        for (Customer customerIn : model.customers){
            System.out.println(customerIn);
        }
        int input = scanner.nextInt();
        Customer customer = new Customer();
        for (Customer customerIn : model.customers){
            if(customerIn.getCustomerId() == input){
                customer = customerIn;
            }
        }

        scanner.nextLine();
        String strDateFormat = "dd-MM-yyyy"; //Date format is Specified
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        System.out.println("Please enter boat Id: ");
        for (Boat boatIn : model.boats){
            System.out.println(boatIn);
        }
        Boat keyInput_boat = null;
        for (int i = 0; i < model.boats.size();i++){
            i = scanner.nextInt() - 1;
            keyInput_boat = model.boats.get(i);
        }
        System.out.println("Enter rental date in the form dd-MM-YYYY");
        scanner.nextLine();
        String enteredDate = scanner.nextLine();
        System.out.println("Enter rental start time in the form 14:30");
        String enteredStartTime = scanner.nextLine();
        System.out.println("Enter rental end time in the form 14:30");
        String enteredEndTime = scanner.nextLine();
        Rental rental = null;
        try {
            rental = new Rental(model.nextRentalId(), objSDF.parse(enteredDate), keyInput_boat, customer,
                    enteredStartTime, enteredEndTime, 2, 70, true, false);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("A new reservation is added successfully.");


        try {
            model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
            model.rentals.add(rental);
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

    private static void showRentals() {
        try {
            model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Rental rental : model.rentals){
            System.out.println(rental);
        }
    }

//    public void execute(RentalList rentals) {
//        this.rentals = rentals;
//
//        while (true) {
//            int choice = RentalMenuView.get();
//        }
//    }
}
