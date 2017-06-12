package com.seleniumsimplified.Synchronisation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

/**
 * Created by drn0342 on 27/04/2017.
 */
public class CustomExpectedConditionTest extends BaseTest{
    private static WebDriverWait wait;
    private static URL url;
    private WebElement webElement;

    @BeforeClass
    public static void startDriver() throws MalformedURLException{
        url = new URL("http://www.compendiumdev.co.uk/selenium/basic_redirect.html");
        wait = new WebDriverWait(driver, 10);
    }

    @Before
    public void goToStartPage(){
        driver.navigate().to(url);
    }

    @Test
    public void textRedirectingIn2Seconds(){
        webElement = wait.until(elementToBeClickable(By.cssSelector("a[id='delaygotobounce']")));
        webElement.click();
        String title = wait.until(titleDoesNotContain("Redirects"));
        assertThat(title, is("Bounce"));
    }

    @Test
    public void textRedirectingIn5Seconds(){
        webElement = wait.until(elementToBeClickable(By.cssSelector("a[id='delaygotobasic']")));
        webElement.click();
        String title = wait.until(titleDoesNotContain("Redirects"));
        assertThat(title, is("Basic Web Page Title"));
    }

    private ExpectedCondition<String> titleDoesNotContain(final String stringNotInTitle)
    {
        return input -> {
            String title = input.getTitle();
            if(title.contains(stringNotInTitle)){
                return null;
            }
            return title;
        };
    }
}
