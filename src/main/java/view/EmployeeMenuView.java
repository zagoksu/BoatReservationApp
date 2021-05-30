package view;

import java.util.Scanner;

public class EmployeeMenuView {
    public static int get() {
        System.out.println("1. show users");
        System.out.println("2. add user");
        System.out.println("3. change user");
        System.out.println("4. delete user");
        System.out.println("0. main menu");

        Scanner sc = new Scanner(System.in);
        while(true) {
            int choice = sc.nextInt();

            if (choice >= 1 || choice <= 5)
                return choice;
        }
    }
}
