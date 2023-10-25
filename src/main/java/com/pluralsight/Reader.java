package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static com.pluralsight.AccountLedgerApp.*;

public class Reader {
    public static void reader() throws IOException {
        information.clear();
        LocalDate localDate;
        LocalTime localTime;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");

        String description;
        String vendor;
        double amount;
        String nextLine;

        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader buffReader = new BufferedReader(fileReader);
        while ((nextLine = buffReader.readLine()) != null) {
            String[] info = nextLine.split(("\\|"));
            if (!info[0].contains("date")) {
                localDate = LocalDate.parse(info[0]);
                localTime = LocalTime.parse(info[1]);
                description = info[2];
                vendor = info[3];
                amount = Double.parseDouble(info[4]);

                information.put(description, new Values(localDate, localTime, description, vendor, amount));
                count++;
                sortValues = new ArrayList<>(information.values());
            }
        }
        buffReader.close();
    }
}
