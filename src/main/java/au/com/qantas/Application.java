package au.com.qantas;


import au.com.qantas.exceptions.DateValidationException;
import au.com.qantas.service.DateService;

import java.util.Scanner;

public class Application {

    public static void main(String... arg) {
        Scanner sc = new Scanner(System.in);

        System.out.println("*****************************************");
        System.out.print("** Enter Start Date in yyyy-MM-dd format : ");
        String startDate = sc.next();
        System.out.print("** Enter End date in yyyy-MM-dd format   : ");
        String endDate = sc.next();
        sc.close();

        try {
            DateService dateService = DateService.build(startDate, endDate);
            System.out.println(String.format("** Total number of days between %s and %s is %d",
                    startDate, endDate, dateService.calculateNumberOfDays()));
        } catch (DateValidationException dve) {
            System.out.println("** " + dve.getMessage());
        }
        System.out.println("*****************************************");

    }
}
