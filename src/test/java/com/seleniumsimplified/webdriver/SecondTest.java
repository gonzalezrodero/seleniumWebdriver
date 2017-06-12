package com.seleniumsimplified.webdriver;

import com.seleniumsimplified.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SecondTest extends BaseTest{
    private static final String ROOT_URL = "http://compendiumdev.co.uk/";
    private long refreshTitle1;
    private long refreshTitle2;

    @BeforeClass
    public static void createDriver(){
        driver = new ChromeDriver();
    }

    @Test
    public void getSeleniumTitleTest(){
        driver.get(ROOT_URL + "/selenium");
        assertThat(driver.getTitle(), startsWith("Selenium Simplified"));
    }

    @Test
    public void getSeleniumSearchTitleTest(){
        driver.get(ROOT_URL + "/selenium" + "/search.php");
        assertThat(driver.getTitle(), containsString("Selenium Simplified Search Engine"));
    }

    @Test
    public void getSeleniumBasicHTMLFormTitleTest(){
        driver.get(ROOT_URL + "/selenium" + "/basic_html_form.html");
        assertThat(driver.getTitle(), containsString("HTML Form Elements"));
    }

    @Test
    public void getSeleniumBasicWebPageTitleTest(){
        driver.get(ROOT_URL + "/selenium" + "/basic_web_page.html");
        assertThat(driver.getTitle(), containsString("Basic Web Page Title"));
    }

    @Test
    public void getSeleniumRefreshTitleTest() throws Exception{
        final String fixedTitle = "Refreshed Page on ";

        URL url = new URL(ROOT_URL + "/selenium" + "/refresh.php");
        driver.navigate().to(url);
        refreshTitle1 = Long.parseLong(driver.getTitle().replaceFirst(fixedTitle, ""));

        try{sleep(2000);}
        catch (Exception e){}

        driver.navigate().refresh();
        refreshTitle2 = Long.parseLong(driver.getTitle().replaceFirst(fixedTitle, ""));

        assertTrue(refreshTitle1 < refreshTitle2);
    }
}
