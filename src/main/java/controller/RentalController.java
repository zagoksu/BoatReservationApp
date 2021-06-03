package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;

import view.RentalMenuView;

import java.io.File;
import java.io.IOException;
import java.text.*;
import java.time.LocalTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.MINUTES;

public class RentalController {
    static Model model = new Model();
    static ObjectMapper mapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);
    static Rental rental = new Rental();

    // check for existing id
    JsonNode rootNode = null;

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
        System.out.println("Please enter discount rate %:");
        int discount = scanner.nextInt();
        System.out.println("IS PAYMENT DONE? Y / N ?");
        scanner.nextLine();
        String keyInput_Payment = scanner.nextLine();
        for (Rental rentalIn : model.rentals){
            if(rentalIn.getRentalId()==rentalId){
                rentalIn.setTotalPrice(rentalIn.getTotalPrice() - (rentalIn.getTotalPrice() * discount / 100));
                System.out.println((rentalIn.getTotalPrice() * discount / 100) + " Euro discount is done.");
                if (keyInput_Payment.equalsIgnoreCase("Y")){
                    rentalIn.setPaymentIsDone(true);
                } else {
                    rentalIn.setPaymentIsDone(false);
                }
                System.out.println("The reservation with id: " + rentalId + " has been updated");
            }
        }
        try {
            mapper.writeValue(new File("src/main/java/model/model.json"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addRentals() throws ParseException {
        List<Integer> bookedBoats = new ArrayList<>();

        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Please select customer:");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-5s", "ID")+ String.format("%-20s", "NAME") + String.format("%-20s", "PHONE NUMBER") + String.format("%-20s", "E-MAIL") + String.format("%-20s", "ADDRESS"));
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (Customer customerIn : model.customers){
            System.out.println(customerIn.getCustomerId() + "\t" + String.format("%-20s", customerIn.getName()) + " \t" +
                    String.format("%-10s", customerIn.getPhoneNumber()) + " \t" + String.format("%-20s", customerIn.getEmail())
                    + "\t" + String.format("%-20s", customerIn.getAddress()));
        }
        int input = scanner.nextInt();
        Customer customer = new Customer();
        for (Customer customerIn : model.customers){
            if(customerIn.getCustomerId() == input){
                customer = customerIn;
            }
        }

        scanner.nextLine();

        Rental rental = null;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        List<String> boatInfo = getBoatInfo();
        showAvailableBoats(boatInfo.get(0),boatInfo.get(1),boatInfo.get(2));

        System.out.println("Please select a boat to make a reservation");
        Boat boat = new Boat();

        int inputBoat = scanner.nextInt();
        for (Boat boatIn : model.boats){
            if(boatIn.getBoatId() == inputBoat){
                boat = boatIn;
            }
        }


        String selectedBoat = scanner.nextLine();

        if (bookedBoats.contains(selectedBoat))
            System.out.println("Please select a boat from the available list");

        System.out.println("Is payment received? Y OR N ?");
        String payment = scanner.nextLine();
        boolean isPaymentDone = false;
        if(payment.equalsIgnoreCase("Y")){
            isPaymentDone = true;
        } else{
            isPaymentDone = false;
        }
        try {
            String strDateFormat = "dd-MM-yyyy"; //Date format is Specified
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

            LocalTime startTime2 = LocalTime.parse(boatInfo.get(1));
            LocalTime endTime2 = LocalTime.parse(boatInfo.get(2));
            double durationInMinutes = MINUTES.between(startTime2, endTime2);
            double rentDuration = durationInMinutes / 60;


            rental = new Rental(model.nextRentalId(), objSDF.parse(boatInfo.get(0)), boat, customer,
                    boatInfo.get(1), boatInfo.get(2), rentDuration,isPaymentDone);
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

    public static List<String> getBoatInfo(){
        System.out.println("Enter rental date in the form dd-MM-YYYY");
        String enteredDate = scanner.nextLine();

        System.out.println("Enter rental start time in the form 14:30");
        String enteredStartTime = scanner.nextLine();

        System.out.println("Enter rental end time in the form 14:30");
        String enteredEndTime = scanner.nextLine();
        List<String> boatInfo = new ArrayList<>();
        boatInfo.add(enteredDate);
        boatInfo.add(enteredStartTime);
        boatInfo.add(enteredEndTime);

        return boatInfo;
    }

    public static void showAvailableBoats(String enteredDate,String enterStartTime, String enterEndTime) {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        List<Integer> bookedBoats = new ArrayList<>();

        // check for existing id
        JsonNode rootNode = null;
        try {


            LocalTime startTime2 = LocalTime.parse(enterStartTime);
            LocalTime endTime2 = LocalTime.parse(enterEndTime);

            System.out.println("time duration of = " + MINUTES.between(startTime2, endTime2));
            rootNode = mapper.readTree(new File("src/main/java/model/model.json"));
            String[] keys = {
                    "boats", "employees", "customers", "rentals"
            };
            for (String key : keys) {
                JsonNode value = rootNode.findValue(key);
                if (key == "rentals") {

                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("List of available boats");
                    System.out.println("Boat Id\tBoat Type\t\t\tSeats\tMinimum price");
                    System.out.println("-----------------------------------------------------------------------------------");

                    for (Rental rental : model.rentals) {
                        Locale locale = new Locale("nl", "NL");
                        String strDateFormat = "dd-MM-yyyy"; //Date format is Specified
                        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
                        LocalTime startTime1 = LocalTime.parse(rental.getStartTime());
                        LocalTime endTime1 = LocalTime.parse(rental.getEndTime());
                        boolean chkOverlap = isOverlapping(startTime1, endTime1, startTime2, endTime2);

                        if (objSDF.parse(enteredDate).equals(rental.getRentDate())) {
                            // add booked boats to a list for later use
                            if (chkOverlap)
                                bookedBoats.add(rental.getBoat().getBoatId());
                        }
                    }
                }
            }

            if (model.boats.size() > 0) {
                for (Boat boat : model.boats) {
                    if (!bookedBoats.contains(boat.getBoatId()))
                        System.out.println(boat.getBoatId() + "\t\t" + boat.getBoatType() + "\t\t\t\t" +
                                boat.getSeats() + "\t\t" + boat.getMinimumPrice());
                }
            }


        } catch (IOException | ParseException e) {
            System.out.println("Please enter the correct information");
//            e.printStackTrace();
            return;
        }
        System.out.println("\n");
    }

    public static boolean isOverlapping(LocalTime startTime1, LocalTime endTime1, LocalTime startTime2, LocalTime endTime2) {
        return !startTime1.isAfter(endTime2) && !startTime2.isAfter(endTime1);
    }

    private static void showRentals() {
        try {
            model = mapper.readValue(new File("src/main/java/model/model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-5s", "ID") + String.format("%-15s", "DATE") + String.format("%-15s", "START TIME")
                + String.format("%-15s", "END TIME") + String.format("%-10s", "BOAT ID") + String.format("%-20s", "BOAT TYPE")
                + String.format("%-15s", "DURATION")  + String.format("%-15s", "TOTAL PRICE") + String.format("%-20s", "CUSTOMER NAME") + String.format("%-20s", "PAYMENT"));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        for (Rental rental : model.rentals){
            String payment = "";
            if (rental.isPaymentIsDone()){
                payment = "DONE";
            } else{
                payment = "NOT DONE";
            }
            Locale locale = new Locale("nl", "NL");
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
            String rentDate = dateFormat.format(rental.getRentDate());

            System.out.println(String.format("%-5s", rental.getRentalId()) + String.format("%-15s", rentDate)
                    + String.format("%-15s", rental.getStartTime()) + String.format("%-15s", rental.getEndTime()) +
                    String.format("%-10s", rental.getBoat().getBoatId())+
                    String.format("%-20s", rental.getBoat().getBoatType()) + String.format("%-15s", rental.getRentDuration())
                            + String.format("%-15s", rental.getTotalPrice()) + String.format("%-20s", rental.getCustomer().getName()) + payment);
    }
}}