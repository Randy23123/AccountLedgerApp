package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Scanner;
import static com.pluralsight.LedgerScreen.*;

public class Home {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<String, Values> information = new HashMap<>();
    static int count = 0;
    Reader reader = new Reader();
    public static ArrayList<Values> sortValues = new ArrayList<>(information.values());

    public static void homeScreen() throws IOException {

        String homeScreen;
        do {
            System.out.println("\nHome Screen");
            System.out.println("1.Deposit");
            System.out.println("2.Payment");
            System.out.println("3.Ledger");
            System.out.println("4.Exit");
            homeScreen = scanner.next();
            scanner.nextLine();

            switch (homeScreen) {
                case "1":
                    deposits();
                    break;
                case "2":
                    payment();
                    break;
                case "3":
                    ledger();
                    break;
                case "4":
                    System.out.println("You have exited application :)");
                    break;
                default:
                    System.out.println("Not an option pick (1-4)\n");
            }
        }
        while (!homeScreen.equals("4"));
    }

    public static void deposits() throws IOException {
        BufferedWriter bufferedWriter;
        bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

        String description;
        String vendor;
        double amount;
        String another;

        while (true) {
            System.out.println("What is the description?");
            description = scanner.nextLine().trim();
            System.out.println("Who is the vendor?");
            vendor = scanner.nextLine().trim();
            System.out.println("What is the amount?");
            amount = scanner.nextDouble();
            scanner.nextLine();

            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");


            bufferedWriter.newLine();
            bufferedWriter.write((localDate + "|" + localTime + "|" + description + "|" + vendor + "|" + amount));
            System.out.println("Its been Stored do you want to Enter another? (Y/N)");
            another = scanner.next().toUpperCase().trim();
            if (!another.equals("Y")) {
                break;
            }
        }
        bufferedWriter.close();
    }

    public static void payment() throws IOException {
        BufferedWriter bufferedWriter;
        bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true));

        String description;
        String vendor;
        double amount;
        String another;

        while (true) {
            System.out.println("What is the description?");
            description = scanner.nextLine().trim();
            System.out.println("Who is the vendor?");
            vendor = scanner.nextLine().trim();
            System.out.println("What is the amount?");
            amount = scanner.nextDouble();
            amount *= -1;
            scanner.nextLine();

            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");


            bufferedWriter.newLine();
            bufferedWriter.write((localDate + "|" + localTime + "|" + description + "|" + vendor + "|" + amount));
            System.out.println("Its been Stored do you want to Enter another? (Y/N)");
            another = scanner.nextLine().toUpperCase().trim();
            if (!another.equals("Y")) {
                break;
            }
        }
        bufferedWriter.close();
    }

}