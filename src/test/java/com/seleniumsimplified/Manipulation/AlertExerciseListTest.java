package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by danielgonzalezrodero on 21/4/17.
 */
public class AlertExerciseListTest extends BaseTest{
    private static URL url;

    @BeforeClass
    public static void setDriver() throws MalformedURLException{
        url = new URL("http://compendiumdev.co.uk/selenium/alerts.html");
        driver.navigate().to(url);
    }

    @Before
    public void refreshSite() throws InterruptedException{
        driver.navigate().refresh();
    }

    /*@Test
    public void acceptAlert(){
        driver.findElement(By.cssSelector("input[id='alertexamples']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I am an alert box!"));
        driver.switchTo().alert().accept();
    }*/

    @Test
    public void dismissAlert(){
        driver.findElement(By.cssSelector("input[id='alertexamples']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I am an alert box!"));
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void acceptConfirmAlert(){
        driver.findElement(By.cssSelector("input[id='confirmexample']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I am a confirm alert"));
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("p[id='confirmreturn']")).getText(), is("true"));
    }

    /*@Test
    public void dismissConfirmAlert(){
        driver.findElement(By.cssSelector("input[id='confirmexample']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I am a confirm alert"));
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.cssSelector("p[id='confirmreturn']")).getText(), is("false"));

    }

    @Test
    public void acceptPromptAlert(){
        driver.findElement(By.cssSelector("input[id='promptexample']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I prompt you"));
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("p[id='promptreturn']")).getText(), is("change me"));
    }

    @Test
    public void dismissPromptAlert(){
        driver.findElement(By.cssSelector("input[id='promptexample']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I prompt you"));
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.cssSelector("p[id='promptreturn']")).getText(), is("pret"));
    }*/

    @Test
    public void acceptPromptAlertTextChanged(){
        driver.findElement(By.cssSelector("input[id='promptexample']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I prompt you"));
        driver.switchTo().alert().sendKeys("My text");
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.cssSelector("p[id='promptreturn']")).getText(), is("My text"));
    }

    @Test
    public void dismissPromptAlertTextChanged(){
        driver.findElement(By.cssSelector("input[id='promptexample']")).click();
        assertThat(driver.switchTo().alert().getText(), is("I prompt you"));
        driver.switchTo().alert().sendKeys("My text");
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.cssSelector("p[id='promptreturn']")).getText(), is("pret"));
    }
}
