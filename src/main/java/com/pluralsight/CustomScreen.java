package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.pluralsight.Home.*;

public class CustomScreen {
    public static void custom(){

        int n = sortValues.size();
        // runs from the beginning of the list it helps in reducing the number of comparisons on each pass
        for (int i = 0; i < n - 1; i++){
            //repeats through the unsorted part of the list
            for (int j = 0; j < n -1; j++) {
                LocalDate date1 = sortValues.get(j).getDate();
                // j = elements in transaction and the +1 is the next element
                LocalDate date2 = sortValues.get(j + 1).getDate();
                //(j + 1) using date1.isAfter(date2) for the current element's date is greater than the next element's date it means they are in the wrong order.
                if (date1.isAfter(date2)){
                    Values temp = sortValues.get(j);
                    sortValues.set(j, sortValues.get(j + 1));
                    sortValues.set(j + 1, temp);
                }
            }
        }

        String customScreen;
        do {
            System.out.println("\nCustom Screen");
            System.out.println("1.Start Date");
            System.out.println("2.End Date");
            System.out.println("3.Description");
            System.out.println("4.Vendor");
            System.out.println("5.Amount");
            System.out.println("0.Go back to Reports");
            customScreen = scanner.nextLine();

            switch (customScreen) {
                case "1":
                    startDate();
                    break;
                case "2":
                    endDate();
                    break;
                case "3":
                    searchDescription();
                    break;
                case "4":
                    searchVendor();
                    break;
                case "5":
                    searchAmount();
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
    public static void startDate(){
        System.out.println("\nSearch by Start Date");
        System.out.println("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();

        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Transactions from the specified year, month, and day:");
        for (Values value : sortValues) {
            LocalDate transactionDate = value.getDate();
            if (transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        value.getDate(), value.getTime(), value.getDescription(), value.getVendor(), value.getAmount());
            }
        }
    }

    public static void endDate(){
        System.out.println("\nSearch by End Date");
        System.out.println("Enter End date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Transactions from the specified year, month, and day:");
        for (Values value : sortValues) {
            LocalDate transactionDate = value.getDate();
            if (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        value.getDate(), value.getTime(), value.getDescription(), value.getVendor(), value.getAmount());
            }
        }
    }

    public static void searchDescription(){
        String user;
        System.out.println("Enter a Description name:");
        user = scanner.next();
        boolean description = false;
        for (Values d : sortValues) {
            String descriptionName = d.getDescription();
            if (descriptionName.equalsIgnoreCase(user)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                description = true;
            }
        }
        if (!description) {
            System.out.println("Not an Option!");
        }
    }
    public static void searchVendor(){
        String user;
        System.out.println("Enter a vendor name:");
        user = scanner.next();
        boolean vendor = false;
        for (Values d : sortValues) {
            String vendorName = d.getVendor();
            if (vendorName.equalsIgnoreCase(user)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                vendor = true;
            }
        }
        if (!vendor) {
            System.out.println("Not an Option!");
        }
    }

    public static void searchAmount(){
        double user;
        System.out.println("Enter an Amount:");
        user = scanner.nextDouble();
        scanner.nextLine();

        boolean amount = false;
        for (Values d : sortValues) {
            double amountValue = d.getAmount();
            //the value 0 if amountValues is equal to user; a value less than 0 if amountValues is less than user
            if (Double.compare(amountValue, user) == 0) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
                amount = true;
            }
        }
        if (!amount) {
            System.out.println("Not an Option!");
        }
    }
}
