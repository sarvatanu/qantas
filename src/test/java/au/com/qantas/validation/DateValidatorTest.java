package au.com.qantas.validation;

import org.junit.Test;

public class DateValidatorTest {

    @Test
    public void testDateValidator_isValidDateShouldReturn_False_When_Year_Is_1900() {
        assert !DateValidator.isValidDate(01, 01, 1900);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_false_When_Year_Is_3000() {
        assert !DateValidator.isValidDate(01, 01, 3000);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_True_When_Year_Is_1901() {
        assert DateValidator.isValidDate(01, 01, 1901);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_True_When_Year_Is_2999() {
        assert DateValidator.isValidDate(31, 12, 2999);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_True() {
        assert DateValidator.isValidDate(2, 2, 2010);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_False() {
        assert !DateValidator.isValidDate(2, 13, 2010);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_False_When_29_Days_In_Non_Leap_Year() {
        assert !DateValidator.isValidDate(29, 2, 2010);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_True_When_29_Days_In_Leap_Year() {
        assert DateValidator.isValidDate(29, 2, 2012);
    }

    @Test
    public void testDateValidator_isValidDateShouldReturn_False_When_30_Days_In_Leap_Year() {
        assert !DateValidator.isValidDate(30, 2, 2012);
    }


    @Test
    public void testDateValidator_isValidFormatShouldReturn_False_When_Year_Is_In_Three_Digit_Format() {
        assert !DateValidator.isValidFormat("959-05-05");
    }

    @Test
    public void testDateValidator_isValidFormatShouldReturn_True_When_Day_Is_In_Single_Digit_Format() {
        assert DateValidator.isValidFormat("2010-05-5");
    }

    @Test
    public void testDateValidator_isValidFormatShouldReturn_True_When_Month_Is_In_Single_Digit_Format() {
        assert DateValidator.isValidFormat("2010-5-5");
    }

    @Test
    public void testDateValidator_isValidFormatShouldReturn_False_When_Day_Is_In_Three_Digit_Format() {
        assert !DateValidator.isValidFormat("2010-05-235");
    }

    @Test
    public void testDateValidator_isValidFormatShouldReturn_False_When_Month_Is_In_Three_Digit_Format() {
        assert !DateValidator.isValidFormat("2010-105-05");
    }

    @Test
    public void testDateValidator_isValidFormatShouldReturn_False_For_dd_mm_yyyy_Format() {
        assert !DateValidator.isValidFormat("05-05-2010");
    }
}
