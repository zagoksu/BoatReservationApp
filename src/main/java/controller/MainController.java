package controller;

import model.Model;
import view.BoatMenuView;
import view.CustomerMenuView;
import view.MainMenuView;
import view.RentalMenuView;

public class MainController {
    Model model;

    public void execute() {
        // Read the model
        model = Model.readModel();

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
                    break outer;
            }


        }
    }

}
