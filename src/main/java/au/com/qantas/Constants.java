package au.com.qantas;

public class Constants {
    public static final String DATE_FORMAT = "^(\\d{4}-\\d{1,2}-\\d{1,2})";
    public static final int NUMBER_OF_DAYS_IN_YEAR = 365;
    public static final int[] NUMBER_OF_MONTH_DAYS_IN_NON_LEAP_YEAR = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int[] NUMBER_OF_MONTH_DAYS_IN_LEAP_YEAR = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final int INT_100 = 100;
    public static final int INT_400 = 400;
    public static final int INT_4 = 4;
    public static final int INT_2 = 2;
    public static final int PRIME_NUMBER = 31;
    public static final int INT_12 = 12;
    public static final int INT_1 = 1;
    public static final int INT_1901 = 1901;
    public static final int INT_2999 = 2999;

    private Constants() {
        throw new IllegalAccessError("Utility class");
    }
}
