package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WindowExerciseListTest extends BaseTest{
    private static WebDriverWait wait;
    private static URL url;

    @BeforeClass
    public static void startTests() throws MalformedURLException{
        wait = new WebDriverWait(driver, 10);
        url = new URL("http://compendiumdev.co.uk/selenium/frames/index.html");
    }

    @Before
    public void goToSite(){
        driver.navigate().to(url);
    }

    @Test
    public void switchToNewWindow() {
        String framesWindowHandle = driver.getWindowHandle();
        switchToFrame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        String newWindowHandle = framesWindowHandle;


        while(newWindowHandle.equals(framesWindowHandle)){
            newWindowHandle = wait.until(windowHandleToBeAvailable());
        }

        switchToWindow(newWindowHandle);
        wait.until(titleContains("Selenium Simplified"));

        assertThat(driver.getTitle(), containsString("Selenium Simplified"));

        switchToWindow(framesWindowHandle);
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
        wait.until(presenceOfElementLocated(By.cssSelector("a[target='compdev']")));
        driver.findElement(By.cssSelector("a[target='compdev']")).click();

        switchToWindow("compdev");
        wait.until(titleContains("Software Testing"));
        assertThat(driver.getTitle(), containsString("Software Testing"));

        switchToWindow("evil");
        assertThat(driver.getTitle(), containsString("EvilTester"));

        switchToWindow(frameWindowHandle);
        assertThat(driver.getTitle(), containsString("Frameset Example"));

        switchToWindow("compdev");
        driver.close();
        assertThat(driver.getWindowHandles().size(), is(2));

        switchToWindow(driver.getWindowHandles().iterator().next());
        driver.close();
        assertThat(driver.getWindowHandles().size(), is(1));

        switchToWindow(driver.getWindowHandles().iterator().next());
    }

    private void switchToFrame(String frame){
        wait.until(frameToBeAvailableAndSwitchToIt(frame));
    }

    private void switchToWindow(String window){
        wait.until(windowToBeAvailable(window));
    }

    private ExpectedCondition<Boolean> windowToBeAvailable(String windowName){
        return new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                try{
                    driver.switchTo().window(windowName);
                    return true;
                } catch (NoSuchWindowException e){
                    return false;
                }
            }
        };
    }

    private ExpectedCondition<String> windowHandleToBeAvailable(){
        return new ExpectedCondition<String>() {
            @Nullable
            @Override
            public String apply(@Nullable WebDriver driver) {
                try{
                    Iterator<String> handle = driver.getWindowHandles().iterator();
                    String lastHandle = handle.next();
                    while(handle.hasNext()){
                        lastHandle = handle.next();
                    }
                    return lastHandle;
                } catch (NoSuchWindowException e){
                    return null;
                }
            }
        };
    }
}