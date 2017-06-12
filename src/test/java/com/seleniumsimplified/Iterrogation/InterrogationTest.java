package com.seleniumsimplified.Iterrogation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by drn0342 on 05/04/2017.
 */
public class InterrogationTest extends BaseTest{
    private static URL url;
    private static final String PROTOCOL = "http://www.";
    private static final String DOMAIN = "compendiumdev.co.uk";
    private static final String PATH = "/selenium/basic_web_page.html";


    @BeforeClass
    public static void startWebDriver() throws MalformedURLException{
        url = new URL(PROTOCOL + DOMAIN + PATH);
        driver.navigate().to(url);
    }

    @Test
    public void webPageTitleTest(){
        String title = "Basic Web Page Title";
        assertThat(driver.getTitle(), is(title));
    }

    @Test
    public void webPageURLTest(){
        assertThat(driver.getCurrentUrl(), is(url.toString()));
    }

    @Test
    public void webPageSourceContainsTextTest(){
        String text = "A paragraph of text";
        assertThat(driver.getPageSource(), containsString(text));
    }
}
