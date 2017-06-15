package com.seleniumsimplified.Synchronisation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by drn0342 on 27/04/2017.
 */
public class FluentWaitTest extends BaseTest{
    private static URL url;
    private static FluentWait<WebElement> fluentWait;
    private static WebDriverWait webDriverWait;
    private WebElement countDown;

    @BeforeClass
    public static void setEnvironment() throws MalformedURLException{
        url = new URL("http://www.compendiumdev.co.uk/selenium/javascript_countdown.html");
        driver.navigate().to(url);
    }

    @Before
    public void refreshBeforeTest(){
        driver.navigate().refresh();
        countDown = driver.findElement(By.cssSelector("div[id='javascript_countdown_time']"));
    }

    @Test
    public void fluentWaitTest(){
        fluentWait = new FluentWait<WebElement>(countDown)
            .withTimeout(20, TimeUnit.SECONDS)
            .pollingEvery(100, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class);

        assertThat(fluentWait.until(countDownSecondsAre("04")), is("01:01:04"));
    }

    @Test
    public void webDriverWaitTest(){
        webDriverWait = new WebDriverWait(driver, 20);
        assertThat(webDriverWait.until(countDownSecondsAreWDW("04")), is("01:01:04"));
    }

    private Function<WebElement, String> countDownSecondsAre(final String seconds) {
        return new Function<WebElement, String>() {
            public String apply(WebElement webElement) {
                String time = webElement.getText();
                if(time.endsWith(seconds)){
                    return time;
                }
                return null;
            }
        };
    }

    private ExpectedCondition<String> countDownSecondsAreWDW(final String seconds){
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                String time = input.findElement(By.cssSelector("div[id='javascript_countdown_time']")).getText();
                if(time.endsWith(seconds)){
                    return time;
                }
                return null;
            }
        };
    }
}
