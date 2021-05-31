package view;

import java.util.Scanner;

public class RentalMenuView {
    public static int get() {
        System.out.println("1. show reservations");
        System.out.println("2. add a new reservation");
        System.out.println("3. change a reservation");
        System.out.println("4. delete a reservation");
        System.out.println("0. main menu");

        Scanner sc = new Scanner(System.in);
        while(true) {
            int choice = sc.nextInt();

            if (choice >= 0 || choice <= 4)
                return choice;
        }
    }
}
