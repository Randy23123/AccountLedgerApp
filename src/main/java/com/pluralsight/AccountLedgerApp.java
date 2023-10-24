package com.pluralsight;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Scanner;

public class AccountLedgerApp {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<String, Deposit> information = new HashMap<>();

    public static void main(String[] args) throws IOException {

        int homeScreen = 400;
        while (homeScreen != 4) {
            System.out.println("Home Screen");
            System.out.println("1.Deposit");
            System.out.println("2.Payment");
            System.out.println("3.Ledger");
            System.out.println("4.Exit");
            homeScreen = scanner.nextInt();
            scanner.nextLine();

            switch (homeScreen) {
                case 1:
                    information();
                    break;
                case 2:
                    payment();
                    break;
                case 3:
                    ledger();
                    break;
                case 4:
                    System.out.println("You have exited application :)");
                    break;
                default:
                    System.out.println("\nNot an option pick (1-4)");
            }
        }
    }
    public static void ledger() throws IOException{

        int ledgerScreen = 400;
        while (ledgerScreen != 5){
            System.out.println("Ledger Screen");
            System.out.println("1.All");
            System.out.println("2.Deposits");
            System.out.println("3.Payments");
            System.out.println("4.Reports");
            System.out.println("5.Home Screen");
            ledgerScreen = scanner.nextInt();
            scanner.nextLine();

            switch (ledgerScreen){
                case 1:


                    break;
                case 2:


                    break;
                case 3:


                    break;
                case 4:


                    break;
                case 5:
                    System.out.println("You have exited to Home Screen :)\n");
                    break;
                default:
                    System.out.println("\nNot an option pick (1-5)");
            }
        }
    }

    public static void payment() throws IOException{
        new FileWriter("src/main/resources/transactions.csv", true);

        LocalDate date1 = LocalDate.now();
        LocalTime date2 = LocalTime.now();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");

        String input = "";
        String today = date1.toString();
        String time = date2.toString();
        String description = "";
        String vendor = "";
        double amount = 0;

        System.out.println("What is the description?");
        description = scanner.nextLine();
        System.out.println("Who is the vendor?");
        vendor = scanner.nextLine();
        System.out.println("What is the amount?");
        amount = scanner.nextDouble();
        scanner.nextLine();


        information.put(description, new Deposit(today, time, description, vendor, amount));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/transactions.csv", true))) {
            bufferedWriter.write((today + "|" + time + "|" + description + "|" + vendor + "|" + "-" + amount + "\n"));
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

        String input = "";
        String today = date1.toString();
        String time = date2.toString();
        String description = "";
        String vendor = "";
        double amount = 0;

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

        String input = "";
        String today = date1.toString();
        String time = date2.toString();
        String description = "";
        String vendor = "";
        double amount = 0;
        boolean firstLine = true;

        new FileReader("src/main/resources/transactions.csv");

        try (BufferedReader buffReader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"))) {
            String nextLine = "";
            while ((nextLine = buffReader.readLine()) != null) {
                if (firstLine){
                    firstLine = false;
                    continue;
                }
                System.out.println(nextLine);
                String[] info = nextLine.split(("\\|"));
                today = info[0];
                time = info[1];
                description = info[2];
                vendor = info[3];
                try {
                    amount = Double.parseDouble(info[4]);
                }catch (NumberFormatException e) {
                    continue;}
            }
        } catch (IOException e) {
            throw new IOException();
        }
        information.put(description, new Deposit(today, time, description, vendor, amount));
    }
}

