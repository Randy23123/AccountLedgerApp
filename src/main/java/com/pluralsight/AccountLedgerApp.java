package com.pluralsight;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccountLedgerApp {
    public static Scanner forPublic = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {

        int homeScreen = 0;
        while (homeScreen != 4){
            System.out.println("Home Screen");
            System.out.println("1.Deposit");
            System.out.println("2.Payment");
            System.out.println("3.Ledger");
            System.out.println("4.Exit");
            homeScreen = forPublic.nextInt();

            switch(homeScreen){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("You have exited application :)");
                    break;
                default:
                    System.out.println("\nNot an option pick (1-4)");
            }
        }
    }
}
