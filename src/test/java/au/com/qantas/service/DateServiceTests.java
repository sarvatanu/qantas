package au.com.qantas.service;

import au.com.qantas.exceptions.DateValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DateServiceTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testDateService_ShouldRaiseDateValidationException_WhenStartDateAndEndDateAreBlank() {
        expectedException.expect(DateValidationException.class);
        expectedException.expectMessage("Start date is invalid. Date should be in yyyy-MM-dd");
        DateService dateService = DateService.build("", "");
    }

    @Test
    public void testDateService_ShouldRaiseDateValidationException_WhenEndDateIsBlank() {
        expectedException.expect(DateValidationException.class);
        expectedException.expectMessage("End date is invalid. Date should be in yyyy-MM-dd");
        DateService dateService = DateService.build("2017-12-01", "");
    }

    @Test
    public void testDateService_ShouldRaiseDateValidationException_WhenEndDateIsInInvalidFormat() {
        expectedException.expect(DateValidationException.class);
        expectedException.expectMessage("End date is invalid. Date should be in yyyy-MM-dd");
        DateService dateService = DateService.build("2017-12-01", "01-01-2018");
    }

    @Test
    public void testDateService_ShouldHaveStartDateAndEndDate_WhenCorrectDateIsPassed() {
        DateService dateService = DateService.build("2017-12-01", "2017-12-02");
        assert dateService.getStartDay() == 01;
        assert dateService.getStartMonth() == 12;
        assert dateService.getStartYear() == 2017;

        assert dateService.getEndDay() == 02;
        assert dateService.getEndMonth() == 12;
        assert dateService.getEndYear() == 2017;

    }

    @Test
    public void testDateService_ShouldHaveStartDateBeforeEndDate_InputStartYearIsGreater() {
        DateService dateService = DateService.build("2018-12-01", "2017-12-02");
        assert dateService.getStartDay() == 02;
        assert dateService.getStartMonth() == 12;
        assert dateService.getStartYear() == 2017;

        assert dateService.getEndDay() == 01;
        assert dateService.getEndMonth() == 12;
        assert dateService.getEndYear() == 2018;
    }

    @Test
    public void testDateService_ShouldHaveStartDateBeforeEndDate_InputStartMonthIsGreater() {
        DateService dateService = DateService.build("2017-12-01", "2017-11-02");
        assert dateService.getStartDay() == 02;
        assert dateService.getStartMonth() == 11;
        assert dateService.getStartYear() == 2017;

        assert dateService.getEndDay() == 01;
        assert dateService.getEndMonth() == 12;
        assert dateService.getEndYear() == 2017;
    }

    @Test
    public void testDateService_ShouldHaveStartDateBeforeEndDate_InputStartDayIsGreater() {
        DateService dateService = DateService.build("2017-12-03", "2017-12-02");
        assert dateService.getStartDay() == 02;
        assert dateService.getStartMonth() == 12;
        assert dateService.getStartYear() == 2017;

        assert dateService.getEndDay() == 03;
        assert dateService.getEndMonth() == 12;
        assert dateService.getEndYear() == 2017;
    }

    @Test
    public void testDateService_isLeapYearShouldReturn_True_WhenYearIs_2000() {
        DateService dateService = DateService.build("2000-12-01", "2017-12-02");
        assert dateService.isLeapYear(dateService.getStartYear());
    }

    @Test
    public void testDateService_isLeapYearShouldReturn_False_WhenYearIs_1800() {
        DateService dateService = DateService.build("1800-12-01", "2017-12-02");
        assert !dateService.isLeapYear(dateService.getStartYear());
    }

    @Test
    public void testDateService_isLeapYearShouldReturn_True_WhenYearIs_1200() {
        DateService dateService = DateService.build("1200-12-01", "2017-12-02");
        assert dateService.isLeapYear(dateService.getStartYear());
    }

    @Test
    public void testDateService_numberOfLeapYearsShouldReturn_3_WhenYear_Between_2000And2010() {
        DateService dateService = DateService.build("2000-01-01", "2010-12-02");
        assert dateService.numberOfLeapYears() == 3;
    }

    @Test
    public void testDateService_numberOfLeapYearsShouldReturn_1_WhenYear_Is_2000() {
        DateService dateService = DateService.build("2000-01-01", "2000-12-02");
        assert dateService.numberOfLeapYears() == 1;
    }

    @Test
    public void testDateService_numberOfLeapYearsShouldReturn_1_WhenYear_Between_1600And2000() {
        DateService dateService = DateService.build("1600-01-01", "2001-12-02");
        assert dateService.numberOfLeapYears() == 98;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_11() {
        DateService dateService = DateService.build("2000-06-13", "2000-06-01");
        assert dateService.calculateNumberOfDays() == 11;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_29() {
        DateService dateService = DateService.build("2000-01-31", "2000-03-01");
        assert dateService.calculateNumberOfDays() == 29;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_30() {
        DateService dateService = DateService.build("2000-02-01", "2000-03-03");
        assert dateService.calculateNumberOfDays() == 30;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_0() {
        DateService dateService = DateService.build("2000-02-01", "2000-02-02");
        assert dateService.calculateNumberOfDays() == 0;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_1() {
        DateService dateService = DateService.build("2000-02-01", "2000-02-02");
        assert dateService.calculateNumberOfDays() == 0;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_0_WhenBothDatesAreSame() {
        DateService dateService = DateService.build("2000-02-01", "2000-02-01");
        assert dateService.calculateNumberOfDays() == 0;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_19() {
        DateService dateService = DateService.build("1983-06-02", "1983-06-22");
        assert dateService.calculateNumberOfDays() == 19;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_173() {
        DateService dateService = DateService.build("1984-07-04", "1984-12-25");
        assert dateService.calculateNumberOfDays() == 173;
    }

    @Test
    public void testDateService_calculateNumberOfDaysShouldReturn_1979() {
        DateService dateService = DateService.build("1983-08-03", "1989-01-03");
        assert dateService.calculateNumberOfDays() == 1979;
    }
}
