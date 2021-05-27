package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Boat;
import model.BoatList;
import model.Customer;
import model.Model;
import view.BoatMenuView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BoatController {
    static Model model = new Model();
    static Scanner scanner = new Scanner(System.in);
    static ObjectMapper mapper = new ObjectMapper();

    public static void execute() {

        outer:
        while (true) {
            int choice = BoatMenuView.get();
            switch (choice) {
                case 1:
                    listBoats();
                    break;
                case 2:
                    addBoat();
                    break;
                case 3:
                    updateBoat();
                    break;
                case 4:
                    deleteBoat(scanner, mapper);
                    break;
                case 5:
                    break outer;
            }


        }
    }

    public static void listBoats(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Boat boatIn : model.boats) {
            System.out.println(boatIn);
        }
    }

    public static Boat addBoat(){
        ObjectMapper mapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);
        String boatType = "";
        int chargingTime = 0;
        int boatSeats = 0;
        double keyInput_boatPrice;

        System.out.println("Please Select Boat Type: \n PRESS 1 FOR ELECTRICAL BOAT \n PRESS 2 FOR KAJAK \n PRESS 3 FOR ROWING BOAT \n PRESS 4 FOR SUPBOARD");
        int keyInput_boatType = scanner.nextInt();
        if(keyInput_boatType == 1){
            boatType = "ELECTRICALBOAT";
            boatSeats = 4;
            chargingTime = 1;

        } else if(keyInput_boatType== 2){
            boatType = "KAJAK";
            boatSeats = 2;
            chargingTime = 0;

        } else if (keyInput_boatType == 3){
            boatType = "ROWINGBOAT";
            boatSeats = 4;
            chargingTime = 0;

        } else if (keyInput_boatType == 4){
            boatType = "SUPBOARD";
            boatSeats = 1;
            chargingTime = 0;
        }
        System.out.println("Please enter boat rent price per hour: ");
        keyInput_boatPrice = scanner.nextInt();
        scanner.nextLine();
        if (keyInput_boatType == 1){
            if(keyInput_boatPrice < 35){
                while (keyInput_boatPrice < 35){
                    System.out.println("The renting price should be minimum 35: ");
                    keyInput_boatPrice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        } else if (keyInput_boatType == 2) {
            if (keyInput_boatPrice < 27.5) {
                while (keyInput_boatPrice < 27.5) {
                    System.out.println("The renting price should be minimum 27.5: ");
                    keyInput_boatPrice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        } else if (keyInput_boatType == 3) {
            if(keyInput_boatPrice < 23){
                while (keyInput_boatPrice < 23){
                    System.out.println("The renting price should be minimum 23: ");
                    keyInput_boatPrice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        }
        else if (keyInput_boatType == 4) {
            if(keyInput_boatPrice < 17.5){
                while (keyInput_boatPrice < 17.5){
                    System.out.println("The renting price should be minimum 17.5: ");
                    keyInput_boatPrice = scanner.nextInt();
                    scanner.nextLine();
                }
            }
        }


        Boat newBoat = new Boat(model.nextBoatId(), boatType, boatSeats, keyInput_boatPrice, chargingTime);
        System.out.println("A new Boat is added successfully.");


        try {
            model = mapper.readValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), Model.class);
            model.boats.add(newBoat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Java object to JSON file
            mapper.writeValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), model);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newBoat;
    }

    public static void updateBoat( ){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the Boat ");
        int boatId = scanner.nextInt();
        System.out.println("Please enter new price for the boat");
        double minimumPrice = scanner.nextDouble();
        for (Boat boatIn : model.boats){
            if(boatIn.getBoatId()==boatId){
                boatIn.setMinimumPrice(minimumPrice);
                System.out.println("The boat with id: " + boatId + " has been updated");
                break;
            }
        }
    }

    public static void deleteBoat(Scanner scanner, ObjectMapper mapper){

        System.out.println("Please enter the Id of the customer you want to delete:");
        int boatId = scanner.nextInt();

        for (Boat boatIn : model.boats){
            if(boatId == boatIn.getBoatId()){
                model.boats.remove(boatIn);
                System.out.println("The customer with id:" + boatId + " has been deleted");
                break;
            }

        }
        // Java object to JSON file
        try {
            mapper.writeValue(new File("C:\\Users\\zgoksu\\IdeaProjects\\BoatReservationApp\\src\\main\\java\\model\\model.json"), model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



