package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Boat;
import model.Model;
import model.Rental;
import view.EmployeeMenuView;
import view.RevenueMenuView;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RevenueController {
    static ObjectMapper mapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);
    static Model model = new Model();

    public static void execute() throws IOException, ParseException {

        outer:
        while (true) {
            int choice = RevenueMenuView.get();
            switch (choice) {
                case 1:
                    showDailyRevenue();
                    break;
                case 2:
                    showTotalRevenue();
                    break;
                case 0:
                    break outer;
            }


        }
    }

    private static void showTotalRevenue() {
        try {
            model = mapper.readValue(new File("src\\main\\java\\model\\model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        double sum = 0;
        for (Rental rental : model.rentals) {
            sum += rental.getTotalPrice();
        }
        System.out.println("Total Revenue:");
        System.out.println(sum + " Euro");

    }

    private static void showDailyRevenue() throws ParseException {
        try {
            model = mapper.readValue(new File("src\\main\\java\\model\\model.json"), Model.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String strDateFormat = "dd-MM-yyyy"; //Date format is Specified
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        System.out.println("Enter rental date in the form dd-MM-YYYY");
        String keyInput_ReportDay = scanner.nextLine();
        double sum = 0;
        Date dateSelected = null;
        for (Rental rental : model.rentals){
            if(rental.getRentDate() == objSDF.parse(keyInput_ReportDay)){ // why gives false?
                sum += rental.getTotalPrice();
                dateSelected = rental.getRentDate();
                System.out.println(dateSelected);
            }
        }
        System.out.println(dateSelected);
        System.out.println(objSDF.parse(keyInput_ReportDay));
        System.out.println("Daily Revenue:");
        System.out.println(sum + " Euro");

}
}

//        int count = 1;
//        for (Rental rental : model.rentals){
//            System.out.println(count + " " + rental.getRentDate());
//            count++;
//
//        }
//        System.out.println("Select date to show revenue");
//        int keyInput_date = scanner.nextInt();
//
//        for(Rental rental : model.rentals){
//            if (keyInput_date == count){
//                System.out.println(rental.getTotalPrice());
//            }
//        }










