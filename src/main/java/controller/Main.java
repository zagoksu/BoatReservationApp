package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Boat;
import model.BoatList;
import model.Employee;
import model.Model;

import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        ObjectMapper mapper = new ObjectMapper();

//        BoatController.listBoats();
        MainController controller = new MainController();
        controller.execute();


    }
}
