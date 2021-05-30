package view;

import java.util.Scanner;

public class RevenueMenuView {
    public static int get() {
        System.out.println("1. SHOW DAILY REVENUE");
        System.out.println("2. SHOW TOTAL REVENUE");
        System.out.println("0. MAIN MENU");

        Scanner sc = new Scanner(System.in);
        while(true) {
            int choice = sc.nextInt();

            if (choice >= 1 || choice <= 3)
                return choice;
        }
    }
}
