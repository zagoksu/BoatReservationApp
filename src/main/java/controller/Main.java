package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Model;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Model model = new Model();
        ObjectMapper mapper = new ObjectMapper();

        MainController controller = new MainController();
        controller.execute();


    }
}
