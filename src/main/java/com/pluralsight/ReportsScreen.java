package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

import static com.pluralsight.Home.*;
import static com.pluralsight.LedgerScreen.*;
import static com.pluralsight.CustomScreen.*;

public class ReportsScreen {
    public static LocalDate dateNow = LocalDate.now();
    public static Year logDateYear = Year.from(dateNow);
    public static Month currentDateMonth = dateNow.getMonth();

    public static void reports() throws IOException {

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
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchVendor();
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

    public static void monthToDate() {
        for (Values d : sortValues) {
            LocalDate transDate = d.getDate();
            Year transYear = Year.from(transDate);
            Month transMonth = transDate.getMonth();
            if (transYear.equals(logDateYear) && transMonth.equals(currentDateMonth)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
        }
    }

    public static void previousMonth() {
        for (Values d : sortValues) {
            LocalDate logDate = d.getDate();
            YearMonth yearMonth = YearMonth.from(logDate);
            YearMonth lastMonth = YearMonth.now().minusMonths(1);
            if (lastMonth.equals(yearMonth)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
        }
    }

    public static void yearToDate() {
        for (Values d : sortValues) {
            LocalDate logDate = d.getDate();
            Year currentYear = Year.from(logDate);
            if (currentYear.equals(logDateYear)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
        }
    }

    public static void previousYear() {
        for (Values d : sortValues) {
            LocalDate logDate = d.getDate();
            Year currentYear = Year.from(logDate);
            Year lastYear = Year.now().minusYears(1);
            if (currentYear.equals(lastYear)) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f\n",
                        d.getDate(), d.getTime(), d.getDescription(), d.getVendor(), d.getAmount());
            }
        }
    }

    public static void searchVendor() {
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
}