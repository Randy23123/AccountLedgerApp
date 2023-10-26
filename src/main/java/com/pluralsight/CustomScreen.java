package com.pluralsight;

import java.io.IOException;
import static com.pluralsight.Home.*;

public class CustomScreen {
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
}
