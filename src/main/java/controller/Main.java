package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Model;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Model model = new Model();
        ObjectMapper mapper = new ObjectMapper();

        MainController controller = new MainController();
        controller.execute();


    }
}
