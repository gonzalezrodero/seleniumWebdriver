package com.seleniumsimplified;

import com.seleniumsimplified.enums.DriverType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.seleniumsimplified.PropertyManager.getProperty;

public class BaseTest {
    private static final int PORT = 4723;
    public static WebDriver driver;

    @BeforeClass
    public static void beforeAll() throws Exception {
        if (getProperty("driver.appium").equals("true")){
            System.setProperty(Driver.SELENIUM2_BASICS_DRIVER, DriverType.APPIUM.toString());
        } else {
            System.setProperty(Driver.SELENIUM2_BASICS_DRIVER, getProperty("driver.default"));
        }
        driver = Driver.get();
    }

    @AfterClass
    public static void afterAll() throws InterruptedException, IOException {
        Driver.quit();
    }
}
