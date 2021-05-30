package view;

import java.util.Scanner;

public class CustomerMenuView {
    public static int get() {
        System.out.println("1. show customers");
        System.out.println("2. add customer");
        System.out.println("3. change customer");
        System.out.println("4. delete customer");
        System.out.println("0. main menu");

        Scanner sc = new Scanner(System.in);
        while(true) {
            int choice = sc.nextInt();

            if (choice >= 1 || choice <= 5)
                return choice;
        }
    }
}
