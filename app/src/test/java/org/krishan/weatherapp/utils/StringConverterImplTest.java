package org.krishan.weatherapp.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringConverterImplTest {
    private StringConverterImpl converter;

    @Before
    public void set_up() {
        converter = StringConverterImpl.newInstance();
    }

    @Test
    public void formatTimeZoneToCity_test_given_not_null_America_New_York_returns_New_York() {
        //Given
        String actual = "America/New_York";

        //expected
        String expected = "New York";

        //assert
        Assert.assertEquals(expected, converter.formatTimeZoneToCity(actual));
    }

    @Test
    public void formatTimeZoneToCity_test_given_null_input_returns_World() {
        //Given
        String input = null;

        //expected
        String expected = "World";

        //assert
        Assert.assertEquals(expected, converter.formatTimeZoneToCity(input));
    }


    @Test
    public void formatTimeZoneToCity_test_given_empty_string_returns_World() {
        //Given
        String input = "";

        //expected
        String expected = "World";

        //assert
        Assert.assertEquals(expected, converter.formatTimeZoneToCity(input));
    }

    @Test
    public void formatDoubleToStringDigit_test_given_positive_double_returns_String() {
        //Given
        double input = 15.12345678;

        //expected
        String expected = "15";

        //assert
        Assert.assertEquals(expected, converter.formatDoubleToStringDigit(input));
    }

    @Test
    public void formatDoubleToStringDigit_test_given_negative_double_returns_String() {
        //Given
        double input = -15.12345678;

        //expected
        String expected = "-15";

        //assert
        Assert.assertEquals(expected, converter.formatDoubleToStringDigit(input));
    }

    @Test
    public void formatDoubleToStringDigit_test_given_zero_double_returns_String() {
        //Given
        double input = 0.00;

        //expected
        String expected = "0";

        //assert
        Assert.assertEquals(expected, converter.formatDoubleToStringDigit(input));
    }

    @After
    public void tear_up() {
        converter = null;
    }
}
