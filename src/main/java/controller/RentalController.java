package controller;

import model.BoatList;
import model.RentalList;

import view.RentalMenuView;

public class RentalController {
    RentalList rentals;

    public static void execute() {
        outer:
        while (true) {
            int choice = RentalMenuView.get();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break outer;
            }


        }
    }

    public void execute(RentalList rentals) {
        this.rentals = rentals;

        while (true) {
            int choice = RentalMenuView.get();
        }
    }
}
