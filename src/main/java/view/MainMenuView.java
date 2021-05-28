package view;

import java.util.Scanner;

public class MainMenuView {
    public static int get() {
        System.out.println("1. Boat List");
        System.out.println("2. Customer List");
        System.out.println("3. Reservation List");
        System.out.println("4. User Menu");
        System.out.println("5. End Program");

        Scanner sc = new Scanner(System.in);
        while(true) {
            int choice = sc.nextInt();

            if (choice >= 1 || choice <= 5)
                return choice;
        }
    }

}
