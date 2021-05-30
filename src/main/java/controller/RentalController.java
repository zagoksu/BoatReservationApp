package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import view.RentalMenuView;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RentalController {
    static Model model = new Model();
    static ObjectMapper mapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);
    static Rental rental = new Rental();

    public static void execute() throws ParseException {
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
                    changeRentals();
                    break;
                case 4:
                    deleteRentals();
                    break;
                case 0:
                    break outer;
            }


        }
    }

    private static void deleteRentals() {
        System.out.println("Please enter the Id of the rental you want to delete:");
        int rentalId = scanner.nextInt();

        for (Rental rental : model.rentals){
            if(rentalId == rental.getRentalId()){
                model.rentals.remove(rental);
                System.out.println("The rental with id:" + rentalId + " has been deleted");
                break;
            }

        }
        // Java object to JSON file
        try {
            mapper.writeValue(new File("src/main/java/model/model.json"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void changeRentals() {
        System.out.println("Please enter the id of the rental ");
        int rentalId = scanner.nextInt();
        System.out.println("Please enter new price for the rental");
        double totalPrice = scanner.nextDouble();
        for (Rental rentalInn : model.rentals){
            if(rental.getRentalId()==rentalId){
                rental.setTotalPrice(totalPrice);
                System.out.println("The reservation with id: " + rentalId + " has been updated");
                break;
            }
        }
        try {
            mapper.writeValue(new File("src/main/java/model/model.json"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addRentals() throws ParseException {
        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
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
        Boat boat = new Boat();
        System.out.println("Please enter boat Id: ");
        for (Boat boatIn : model.boats){
            System.out.println(boatIn);
        }
        int inputBoat = scanner.nextInt();
        for (Boat boatIn : model.boats){
            if(boatIn.getBoatId() == inputBoat){
                boat = boatIn;
            }
        }
        System.out.println("Enter rental date in the form dd-MM-YYYY");
        scanner.nextLine();
        String enteredDate = scanner.nextLine();
        System.out.println("Enter rental start time in the form 14:30");
        String enteredStartTime = scanner.nextLine();
        System.out.println("Enter rental end time in the form 14:30");
        String enteredEndTime = scanner.nextLine();
        Rental rental = null;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(enteredStartTime);
        Date date2 = format.parse(enteredEndTime);
        double difference =  date2.getTime() - date1.getTime();
        double rentDuration = difference / 3_600_000;
        System.out.println("Is payment received? Y OR N ?");
        String payment = scanner.nextLine();
        boolean isPaymentDone = false;
        if(payment.equalsIgnoreCase("Y")){
            isPaymentDone = true;
        } else{
            isPaymentDone = false;
        }
        try {
            rental = new Rental(model.nextRentalId(), objSDF.parse(enteredDate), boat, customer,
                    enteredStartTime, enteredEndTime, rentDuration,isPaymentDone);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("A new reservation is added successfully.");


        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
            model.rentals.add(rental);
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

    private static void showRentals() {
        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("No\tDate\t \t\t\t\t\t\tStart Time\tEnd Time\tBoat Id\tBoat Type \t\t Duration \t Total Price\tCustomer Name\t Payment");
        for (Rental rental : model.rentals){
            String payment = "";
            if (rental.isPaymentIsDone()){
                payment = "DONE";
            } else{
                payment = "NOT DONE";
            }
            System.out.println(rental.getRentalId() + "\t" + rental.getRentDate() + "\t" + rental.getStartTime() + "\t\t"
                    + rental.getEndTime() + "\t\t" + rental.getBoat().getBoatId() + "\t\t\t" + rental.getBoat().getBoatType() + "\t\t\t" +
                    rental.getRentDuration() + "\t\t\t" + rental.getTotalPrice() + "\t\t" + rental.getCustomer().getName() + "\t\t\t" + payment);
        }
        System.out.println("\n");
    }
}
