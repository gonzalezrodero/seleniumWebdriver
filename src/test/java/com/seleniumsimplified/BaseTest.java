package com.seleniumsimplified;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    private static final int PORT = 4723;
    public static WebDriver driver;

    @BeforeClass
    public static void beforeAll() throws Exception {
        driver = Driver.get();
    }

    @AfterClass
    public static void afterAll() throws InterruptedException, IOException {
        Driver.quit();
    }
}
