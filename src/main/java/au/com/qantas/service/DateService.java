package au.com.qantas.service;

import au.com.qantas.Constants;
import au.com.qantas.exceptions.DateValidationException;
import au.com.qantas.validation.DateValidator;

import java.util.ArrayList;
import java.util.List;

import static au.com.qantas.Constants.INT_100;
import static au.com.qantas.Constants.INT_2;
import static au.com.qantas.Constants.INT_4;
import static au.com.qantas.Constants.INT_400;
import static au.com.qantas.Constants.PRIME_NUMBER;

public final class DateService {

    private final int startDay;
    private final int startMonth;
    private final int startYear;

    private final int endDay;
    private final int endMonth;
    private final int endYear;

    private DateService(final String startDate,
                        final String endDate) {

        /**
         * Check for Invalid date format
         */
        if (!DateValidator.isValidFormat(startDate)) {
            throw new DateValidationException("Start date is invalid. Date should be in yyyy-MM-dd");
        }

        if (!DateValidator.isValidFormat(endDate)) {
            throw new DateValidationException("End date is invalid. Date should be in yyyy-MM-dd");
        }

        String[] startDateComponents = startDate.split("-");
        String[] endDateComponents = endDate.split("-");

        List<Integer> parsedStartDate = parseDateToList(startDateComponents);
        List<Integer> parsedEndDate = parseDateToList(endDateComponents);

        /**
         * Check for Invalid date components
         */
        if (!DateValidator.isValidDate(parsedStartDate.get(0), parsedStartDate.get(1), parsedStartDate.get(2))) {
            throw new DateValidationException("Invalid Start Date " + startDate);
        }
        if (!DateValidator.isValidDate(parsedEndDate.get(0), parsedEndDate.get(1), parsedEndDate.get(2))) {
            throw new DateValidationException("Invalid End Date " + endDate);
        }

        /**
         * swapping logic when input start date is grater than input end date
         */
        if (parsedEndDate.get(2).intValue() < parsedStartDate.get(2).intValue()) {
            parsedStartDate = parseDateToList(endDateComponents);
            parsedEndDate = parseDateToList(startDateComponents);
        }

        if (parsedEndDate.get(2).equals(parsedStartDate.get(2))) {
            if (parsedEndDate.get(1).intValue() < parsedStartDate.get(1).intValue()) {
                parsedStartDate = parseDateToList(endDateComponents);
                parsedEndDate = parseDateToList(startDateComponents);
            } else if (parsedEndDate.get(1).equals(parsedStartDate.get(1))
                    && parsedEndDate.get(0).intValue() < parsedStartDate.get(0).intValue()) {
                parsedStartDate = parseDateToList(endDateComponents);
                parsedEndDate = parseDateToList(startDateComponents);
            }
        }
        this.startDay = parsedStartDate.get(0);
        this.startMonth = parsedStartDate.get(1);
        this.startYear = parsedStartDate.get(2);
        this.endDay = parsedEndDate.get(0);
        this.endMonth = parsedEndDate.get(1);
        this.endYear = parsedEndDate.get(2);
    }

    /**
     * Static method to build immutable DateService object
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static DateService build(final String startDate,
                                    final String endDate) {
        return new DateService(startDate, endDate);
    }

    /**
     * public method to calculate the number of days
     *
     * @return
     */
    public int calculateNumberOfDays() {
        int noOfDaysInFullYears = getNoOfFullYears() * Constants.NUMBER_OF_DAYS_IN_YEAR;

        int noOfDays = numberOfLeapYears();

        if (startYear == endYear) {
            int noOfDaysFromBeginingOfStratDate = numberOfDays(startMonth, startDay);
            int noOfDaysFromBeginingOfEndDate = numberOfDays(endMonth, endDay) - 1;
            noOfDays += (noOfDaysFromBeginingOfEndDate - noOfDaysFromBeginingOfStratDate);
        } else {
            int noOfDaysFromBeginingOfStratDate = Constants.NUMBER_OF_DAYS_IN_YEAR - numberOfDays(startMonth, startDay);
            int noOfDaysFromBeginingOfEndDate = numberOfDays(endMonth, endDay) - 1;
            noOfDays += (noOfDaysFromBeginingOfEndDate + noOfDaysFromBeginingOfStratDate) + noOfDaysInFullYears;
        }
        return getReturnVal(noOfDays);
    }

    private int getReturnVal(final int val) {
        if (val > 0) {
            return val;
        } else {
            return 0;
        }
    }

    private List<Integer> parseDateToList(final String[] date) {
        List<Integer> dateToList = new ArrayList();
        dateToList.add(Integer.parseInt(date[2]));
        dateToList.add(Integer.parseInt(date[1]));
        dateToList.add(Integer.parseInt(date[0]));
        return dateToList;
    }

    private int numberOfDays(final int month,
                             final int days) {
        int totalDays = 0;
        for (int index = 0; index < month - 1; index++) {
            totalDays += Constants.NUMBER_OF_MONTH_DAYS_IN_NON_LEAP_YEAR[index];
        }
        totalDays += days;
        return totalDays;
    }

    protected boolean isLeapYear(final int year) {
        return (((year % INT_4 == 0) && (year % INT_100 != 0)) || (year % INT_400 == 0));
    }

    protected int numberOfLeapYears() {
        int startleapYears = getTotalLeapYears(startYear, startMonth);
        int endleapYears = getTotalLeapYears(endYear, endMonth);
        return endleapYears - startleapYears;
    }

    private int getTotalLeapYears(int year,
                                  int month) {
        if (month <= INT_2) {
            year--;
        }
        return ((year / INT_4) - (year / INT_100) + (year / INT_400));
    }

    private int getNoOfFullYears() {
        int diff = (this.endYear - (this.startYear + 1));
        return getReturnVal(diff);
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DateService that = (DateService) o;

        if (startDay != that.startDay) {
            return false;
        }
        if (startMonth != that.startMonth) {
            return false;
        }
        if (startYear != that.startYear) {
            return false;
        }
        if (endDay != that.endDay) {
            return false;
        }
        if (endMonth != that.endMonth) {
            return false;
        }
        if (endYear != that.endYear) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = startDay;
        result = PRIME_NUMBER * result + startMonth;
        result = PRIME_NUMBER * result + startYear;
        result = PRIME_NUMBER * result + endDay;
        result = PRIME_NUMBER * result + endMonth;
        result = PRIME_NUMBER * result + endYear;
        return result;
    }

    @Override
    public String toString() {
        return "DateService{" +
                "startDay=" + startDay +
                ", startMonth=" + startMonth +
                ", startYear=" + startYear +
                ", endDay=" + endDay +
                ", endMonth=" + endMonth +
                ", endYear=" + endYear +
                '}';
    }
}
