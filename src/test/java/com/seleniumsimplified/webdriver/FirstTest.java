package com.seleniumsimplified.webdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by drn0342 on 27/03/2017.
 */
public class FirstTest {


    @Test
    public void driverIsTheKing()
    {
        //WebDriver driver = new HtmlUnitDriver();
        //WebDriver driver = new FirefoxDriver();
        //System.setProperty("webdriver.gecko.driver","/Users/danielgonzalezrodero/dependencies/webdrivers/gecko");
        //System.setProperty("webdriver.chrome.driver","/Users/danielgonzalezrodero/dependencies/webdrivers/chrome");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
        driver.quit();
    }
}
