package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.Scanner;

public class AccountLedgerApp {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<String, Values> information = new HashMap<>();
    static int count = 0;
    Reader reader = new Reader();

    public static void main(String[] args) throws IOException {
        //reads transaction file and loads the hashmap
        Reader.reader();
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

    public static void ledger() throws IOException {
        Reader.reader();

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

            switch (ledgerScreen) {
                case "1":
                    displayAll();
                    break;
                case "2":
                    displayDeposits();
                    break;
                case "3":
                    displayPayments();
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
        while (!ledgerScreen.equals("5"));
    }
    public static void displayAll() throws IOException{
        for (Values d : information.values()) {
            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
        }
    }
    public static void displayDeposits() throws IOException{
        for (Values d : information.values())
            if (d.getAmount() > 0) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
    }
    public static void  displayPayments() throws  IOException{
        for (Values d : information.values())
            if (d.getAmount() < 0) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
    }

    public static void reports() throws IOException {
        LocalDate dateNow = LocalDate.now();
        Year logDateYear = Year.from(dateNow);
        Month currentDateMonth = dateNow.getMonth();


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
            switch (reportScreen) {
                case "1":
                    for (Values d : information.values()) {
                        LocalDate transDate = d.getToday();
                        Year transYear = Year.from(transDate);
                        Month transMonth = transDate.getMonth();
                        if (transYear.equals(logDateYear) && transMonth.equals(currentDateMonth)) {
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "2":
                    for (Values d : information.values()) {
                        LocalDate logDate = d.getToday();
                        YearMonth yearMonth = YearMonth.from(logDate);
                        YearMonth lastMonth = YearMonth.now().minusMonths(1);
                        if (lastMonth.equals(yearMonth)) {
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "3":
                    for (Values d : information.values()) {
                        LocalDate logDate = d.getToday();
                        Year currentYear = Year.from(logDate);
                        if (currentYear.equals(logDateYear)) {
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "4":
                    for (Values d : information.values()) {
                        LocalDate logDate = d.getToday();
                        Year currentYear = Year.from(logDate);
                        Year lastYear = Year.now().minusYears(1);
                        if (currentYear.equals(lastYear)) {
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                        }
                    }
                    break;
                case "5":
                    String user;
                    System.out.println("Enter a vendor name:");
                    user = scanner.next();
                    boolean vendor = false;
                    for (Values d : information.values()) {
                        String vendorName = d.getVendor();
                        if (vendorName.equalsIgnoreCase(user)) {
                            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                                    d.getToday(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                            vendor = true;
                        }
                    }
                    if (!vendor){
                        System.out.println("Not an Option!");
                    }

                    break;
                case "6":
                    custom();
                    break;
                case "0":
                    System.out.println("You have exited to Ledger Screen :)\n");
                    ledger();
                    break;
                default:
                    System.out.println("Not an option pick (1-6) or 0 to go Back\n");
            }
        }
        while (!reportScreen.equals("0"));
    }

    public static void custom() throws IOException {
        String customScreen;
        do {
            System.out.println("Custom Screen");
            System.out.println("1.Start Date");
            System.out.println("2.End Date");
            System.out.println("3.Description");
            System.out.println("4.Vendor");
            System.out.println("5.Amount");
            System.out.println("0.Go back to Reports");
            customScreen = scanner.nextLine();
            scanner.nextLine();

            switch (customScreen) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "0":
                    System.out.println("Exiting to Report :)");
                    break;
                default:
                    System.out.println("Not an option pick (1-5) or 0 to Exit\n");
            }
        }
        while (!customScreen.equals("0"));
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
            description = scanner.next();
            System.out.println("Who is the vendor?");
            vendor = scanner.next();
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
            another = scanner.next().toUpperCase().trim();
            if (!another.equals("Y")){
                break;
            }
        }
        bufferedWriter.close();
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
            description = scanner.next();
            System.out.println("Who is the vendor?");
            vendor = scanner.next();
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
            if (!another.equals("Y")){
                break;
            }
        }
        bufferedWriter.close();
    }
}