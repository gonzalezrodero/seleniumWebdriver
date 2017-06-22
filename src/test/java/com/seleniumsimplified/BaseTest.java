package com.seleniumsimplified;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.seleniumsimplified.Driver.*;

public class BaseTest {
    public static WebDriver driver;
    public static Driver webDriver;

    public BaseTest(){
        webDriver = new Driver();
    }

    @BeforeClass
    public static void beforeAll() throws Exception {
        setWebDriver();
        driver = getWebDriver();
    }

    @AfterClass
    public static void afterAll() throws InterruptedException, IOException {
        quitWebDriver();
    }
}
