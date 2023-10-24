package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Scanner;

public class AccountLedgerApp {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<String, Deposit> information = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //reads transaction file and loads the hashmap
        time();
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
                    information();
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
    public static void ledger() throws IOException{

        String ledgerScreen;
        do {
            System.out.println("Ledger Screen");
            System.out.println("1.Display All");
            System.out.println("2.Display Deposits");
            System.out.println("3.Display Payments");
            System.out.println("4.Reports Screen");
            System.out.println("5.Home Screen");
            ledgerScreen = scanner.next();
            scanner.nextLine();

            switch (ledgerScreen){
                case "1":
                    for (Deposit d : information.values()){
                        System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                    }
                    break;
                case "2":
                    for (Deposit d : information.values())
                        if (d.getAmount() > 0){
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    break;
                case "3":
                    for (Deposit d : information.values())
                        if (d.getAmount() < 0){
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    break;
                case "4":
                    reports();
                    break;
                case "5":
                    System.out.println("You have exited to Home Screen :)\n");
                    break;
                default:
                    System.out.println("Not an option pick (1-5)\n");
            }
        }
        while(!ledgerScreen.equals("5"));
    }
    public static void reports() throws IOException{
        LocalDate dateNow = LocalDate.now();
        Year logDateYear = Year.from(dateNow);

        String reportScreen;
        do {
            System.out.println("Reports Screen");
            System.out.println("1.Month to date");
            System.out.println("2.Previous Month");
            System.out.println("3.Year to Date");
            System.out.println("4.Previous Year");
            System.out.println("5.Search by Vendor");
            System.out.println("6.Custom Search");
            System.out.println("0.Go back to Ledger Screen");
            reportScreen = scanner.next();
            scanner.nextLine();
            switch(reportScreen){
                case "1":
                    for (Deposit d : information.values()){
                        Month currentMonth  = dateNow.getMonth();
                        if (dateNow.getMonth() == currentMonth){
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "2":
                for (Deposit d : information.values()){
                    LocalDate logDate = d.getToday();
                    YearMonth yearMonth = YearMonth.from(logDate);
                    YearMonth lastMonth = YearMonth.now().minusMonths(1);
                    if (lastMonth.equals(yearMonth)){
                        System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                    }
                }
                    break;
                case "3":
                    for (Deposit d : information.values()){
                        LocalDate logDate = d.getToday();
                        Year currentYear = Year.from(logDate);
                        if (currentYear.equals(logDateYear)){
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "4":
                    for (Deposit d : information.values()){
                        LocalDate logDate = d.getToday();
                        Year currentYear = Year.from(logDate);
                        Year lastYear = Year.now().minusYears(1);
                        if (currentYear.equals(lastYear)){
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "5":
                    System.out.println("Enter a vendor name:");
                    scanner.nextLine();
                    for (Deposit d : information.values()){

                    }
                    break;
                case "6":
                    break;
                case "0":
                    System.out.println("You have exited to Ledger Screen :)\n");
                    ledger();
                    break;
                default:
                    System.out.println("Not an option pick (1-6) or 0 to go Back\n");
            }
        }
        while(!reportScreen.equals("0"));
    }

    public static void payment() throws IOException{
        new FileWriter("src/main/resources/transactions.csv", true);

        LocalDate date1 = LocalDate.now();
        LocalTime date2 = LocalTime.now();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");


        LocalDate today = date1;
        LocalTime time = date2;
        String description;
        String vendor;
        double amount;

        System.out.println("What is the description?");
        description = scanner.nextLine();
        System.out.println("Who is the vendor?");
        vendor = scanner.nextLine();
        System.out.println("What is the amount?");
        amount = scanner.nextDouble();
        amount *= -1;
        scanner.nextLine();


        information.put(description, new Deposit(today, time, description, vendor, amount));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true))) {
            bufferedWriter.write((today + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n"));
            System.out.println("Payment has been Recorded :)\n");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public static void information() throws IOException {
        new FileWriter("src/main/resources/transactions.csv", true);

        LocalDate date1 = LocalDate.now();
        LocalTime date2 = LocalTime.now();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");


        LocalDate today = date1;
        LocalTime time = date2;
        String description;
        String vendor;
        double amount;

        System.out.println("What is the description?");
        description = scanner.nextLine();
        System.out.println("Who is the vendor?");
        vendor = scanner.nextLine();
        System.out.println("What is the amount?");
        amount = scanner.nextDouble();
        scanner.nextLine();


        information.put(description, new Deposit(today, time, description, vendor, amount));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true))) {
            bufferedWriter.write((today + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n"));
        } catch (IOException e) {
            throw new IOException();
        }
    }
    public static void time() throws IOException{
        LocalDate date1 = LocalDate.now();
        LocalTime date2 = LocalTime.now();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");

        LocalDate today = date1;
        LocalTime time = date2;
        String description;
        String vendor;
        double amount;
        boolean firstLine = true;

        new FileReader("src/main/resources/transactions.csv");

        try (BufferedReader buffReader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String nextLine;
            while ((nextLine = buffReader.readLine()) != null) {
                if (firstLine){
                    firstLine = false;
                    continue;
                }
                System.out.println(nextLine);
                String[] info = nextLine.split(("\\|"));
                today = LocalDate.parse(info[0]);
                time = LocalTime.parse(info[1]);
                description = info[2];
                vendor = info[3];
                amount = Double.parseDouble(info[4]);
                information.put(description, new Deposit(today, time, description, vendor, amount));
            }
        } catch (IOException e) {
            throw new IOException();
        }

    }
}