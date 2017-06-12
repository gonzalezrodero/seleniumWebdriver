package com.seleniumsimplified.Iterrogation;

import com.seleniumsimplified.BaseTest;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GetTitleReplacementExerciseTest extends BaseTest {
    private static final String url = "http://www.compendiumdev.co.uk/" + "selenium/find_by_playground.php";
    private WebElement webElement;

    @BeforeClass
    public static void startDriver(){
        driver.get(url);
    }

    @Test
    public void CSSTest(){
        webElement = driver.findElement(By.cssSelector("title"));
        assertThat(webElement.getAttribute("innerHTML"), is(driver.getTitle()));
    }

    @Test
    public void XPathTest(){
        webElement = driver.findElement(By.xpath("//title"));
        assertThat(webElement.getAttribute("innerHTML"), is(driver.getTitle()));
    }
}
