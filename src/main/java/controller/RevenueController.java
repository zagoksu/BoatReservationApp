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
            if(rental.getRentDate().equals(objSDF.parse(keyInput_ReportDay))){
                sum += rental.getTotalPrice();
                dateSelected = rental.getRentDate();
            }
        }
        System.out.println("Daily Revenue on " + dateSelected + " :");
        System.out.println(sum + " Euro");

}
}











