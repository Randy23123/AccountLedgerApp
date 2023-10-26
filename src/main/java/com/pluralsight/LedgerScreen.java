package com.pluralsight;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.pluralsight.Home.*;
import static com.pluralsight.ReportsScreen.*;

public class LedgerScreen {
    public static void ledger() throws IOException {
        Reader.reader();

        for (int i = 0; i < sortValues.size() - 1; i++) {
            for (int j = i + 1; j < sortValues.size(); j++) {
                Values value1 = sortValues.get(i);
                Values value2 = sortValues.get(j);
                LocalDateTime dateTime1 = LocalDateTime.of(value1.getDate(), value1.getTime());
                LocalDateTime dateTime2 = LocalDateTime.of(value2.getDate(), value2.getTime());

                if (dateTime2.isAfter(dateTime1)) {
                    Values temp = sortValues.get(i);
                    sortValues.set(i, sortValues.get(j));
                    sortValues.set(j, temp);
                }
            }
        }

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

    public static void displayAll() {
        for (Values d : sortValues) {
            System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                    d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
        }
    }

    public static void displayDeposits() {
        for (Values d : sortValues)
            if (d.getAmount() > 0) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
    }

    public static void displayPayments() {
        for (Values d : sortValues)
            if (d.getAmount() < 0) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
    }
}
