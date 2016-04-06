package com.sdl.selenium.bootstrap.form;

import com.sdl.selenium.InputData;
import com.sdl.selenium.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DatePickerIntegrationTest extends TestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatePickerIntegrationTest.class);

    private Form form = new Form(null, "Form Title");
    private DatePicker datePicker = new DatePicker(form, "dp3");

    @BeforeClass
    public void startTests() {
        driver.get(InputData.BOOTSTRAP_URL);
    }

    // TODO bug - select 29/01 will select the 29 of preview month

    @Test
    public void selectDate() {
        long startMs = System.currentTimeMillis();
        datePicker.select("19/02/2016");
        long endMs = System.currentTimeMillis();
        LOGGER.info(String.format("selectDate1 took %s ms", endMs - startMs));
        assertTrue("19-02-2016".equals(datePicker.getDate()));

        startMs = System.currentTimeMillis();
        datePicker.select("22/09/2016");
        endMs = System.currentTimeMillis();
        LOGGER.info(String.format("selectDate2 took %s ms", endMs - startMs));
        assertTrue("22-09-2016".equals(datePicker.getDate()));

        startMs = System.currentTimeMillis();
        datePicker.select("12/09/2016");
        endMs = System.currentTimeMillis();
        LOGGER.info(String.format("selectDate3 took %s ms", endMs - startMs));
        assertTrue("12-09-2016".equals(datePicker.getDate()));

        startMs = System.currentTimeMillis();
        datePicker.select("12/09/2016");
        endMs = System.currentTimeMillis();
        LOGGER.info(String.format("selectDate4 took %s ms", endMs - startMs));
        assertTrue("12-09-2016".equals(datePicker.getDate()));

        startMs = System.currentTimeMillis();
        datePicker.select("29/01/2016");
        endMs = System.currentTimeMillis();
        LOGGER.info(String.format("selectDate5 took %s ms", endMs - startMs));
        assertTrue("29-01-2016".equals(datePicker.getDate()));
    }

    @Test
    public void selectOldDate() {
        datePicker.select("11/08/1872");
        assertTrue("11-08-1872".equals(datePicker.getDate()));
    }

    @Test
    public void selectNewDate() {
        datePicker.select("02/08/2052");
        assertTrue("02-08-2052".equals(datePicker.getDate()));
    }
}
