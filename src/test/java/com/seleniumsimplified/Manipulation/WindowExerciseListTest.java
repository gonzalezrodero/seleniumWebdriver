package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class WindowExerciseListTest extends BaseTest{
    private static WebDriverWait wait;
    private static URL url;

    @BeforeClass
    public static void startTests() throws MalformedURLException{
        wait = new WebDriverWait(driver, 300);
        url = new URL("http://compendiumdev.co.uk/selenium/frames/index.html");
        driver.navigate().to(url);
    }

    @Test
    public void switchToNewWindow() {
        String framesWindowHandle = driver.getWindowHandle();
        switchToFrame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        String newWindowHandle = framesWindowHandle;

        Iterator<String> handle = driver.getWindowHandles().iterator();
        while(newWindowHandle.equals(framesWindowHandle)){
            newWindowHandle = handle.next();
        }

        switchToWindow(newWindowHandle);
        wait.until(titleContains("Selenium Simplified"));

        assertThat(driver.getTitle(), containsString("Selenium Simplified"));

        switchToWindow(framesWindowHandle);
        switchToFrame("content");
        assertThat(driver.getTitle(), containsString("Frameset Example"));

        switchToWindow(newWindowHandle);
        driver.close();
        switchToWindow(framesWindowHandle);
    }

    @Test
    public void switchToByName(){
        String frameWindowHandle = driver.getWindowHandle();

        switchToFrame("content");
        driver.findElement(By.cssSelector("a[id='goevil']")).click();
        driver.findElement(By.cssSelector("a[target='compdev']")).click();

        switchToWindow("compdev");
        assertThat(driver.getTitle(), containsString("Software Testing"));

        switchToWindow("evil");
        assertThat(driver.getTitle(), containsString("EvilTester"));

        switchToWindow(frameWindowHandle);
        assertThat(driver.getTitle(), containsString("Frameset Example"));

        switchToWindow("compdev");
        driver.close();
        assertThat(driver.getWindowHandles().size(), is(2));

        switchToWindow("evil");
        driver.close();
        assertThat(driver.getWindowHandles().size(), is(1));
    }

    private void switchToFrame(String frame)
    {
        driver.switchTo().frame(frame);
    }

    private void switchToWindow(String window)
    {
        driver.switchTo().window(window);
    }
}