package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
    public List<Boat> boats =  new ArrayList<>();
    public List<Employee> employees = new ArrayList<>();
    public List<Customer> customers = new ArrayList<>();
    public List<Rental> rentals = new ArrayList<>();

//    BoatList boats = new BoatList();
//    CustomerList customers = new CustomerList();
//    RentalList rentals = new RentalList();
//    EmployeeList employees = new EmployeeList();


    Scanner scanner = new Scanner(System.in);
    ObjectMapper mapper = new ObjectMapper();

    public int nextBoatId(){
        int id = 0;
        for (Boat boatIn : boats ){
            if (boatIn.getBoatId() > id){
                id = boatIn.getBoatId();
            }
        }
        id++;
        return id;
    }

    public int nextCustomerId(){
        int id = 0;
        for (Customer customerIn : customers ){
            if (customerIn.getCustomerId() > id){
                id = customerIn.getCustomerId();
            }
        }
        id++;
        return id;
    }

    public int nextRentalId(){
        int id = 0;
        for (Rental rentalIn : rentals ){
            if (rentalIn.getRentalId() > id){
                id = rentalIn.getRentalId();
            }
        }
        id++;
        return id;
    }

    public static Model readModel() {
        return null;
    }
}
