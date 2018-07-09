package au.com.qantas.validation;

import au.com.qantas.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static au.com.qantas.Constants.DATE_FORMAT;
import static au.com.qantas.Constants.INT_1;
import static au.com.qantas.Constants.INT_100;
import static au.com.qantas.Constants.INT_12;
import static au.com.qantas.Constants.INT_1901;
import static au.com.qantas.Constants.INT_2999;
import static au.com.qantas.Constants.INT_4;
import static au.com.qantas.Constants.INT_400;

public class DateValidator {


    private DateValidator() {
        throw new IllegalAccessError("Utility class");
    }

    public static boolean isValidDate(final int day,
                                      final int month,
                                      final int year) {
        if (year < INT_1901 || year > INT_2999) {
            return false;
        }

        if (month < INT_1 || month > INT_12) {
            return false;
        }

        boolean isLeapYear = isLeapYear(year);
        if (isLeapYear && day > Constants.NUMBER_OF_MONTH_DAYS_IN_LEAP_YEAR[month - 1]) {
            return false;
        }

        if (!isLeapYear && day > Constants.NUMBER_OF_MONTH_DAYS_IN_NON_LEAP_YEAR[month - 1]) {
            return false;
        }

        return true;
    }

    public static boolean isValidFormat(final String date) {
        Pattern datePattern = Pattern.compile(DATE_FORMAT);
        Matcher matcher = datePattern.matcher(date);
        return matcher.matches();
    }

    private static boolean isLeapYear(final int year) {
        return (((year % INT_4 == 0) && (year % INT_100 != 0)) || (year % INT_400 == 0));
    }
}
