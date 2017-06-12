package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FrameExerciseListTest extends BaseTest{
    private static URL url;

    @BeforeClass
    public static void setDriver() throws MalformedURLException{
        url = new URL("http://www.compendiumdev.co.uk/selenium/frames");
    }

    @Before
    public void goToStartPage(){
        driver.navigate().to(url);
    }

    @Test
    public void h1Content(){
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='green.html']")).click();
        assertThat(driver.findElement(By.cssSelector("h1[id='green']")).getText(), is("Green Page"));
        driver.findElement(By.cssSelector("a[href='content.html']")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Content"));
    }

    @Test
    public void mainFramesPage(){
        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='iframe.html']")).click();
        assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("Iframe Below"));
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("a[href='frames_example_5.html']")).click();
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='index.html']")).click();
        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));
    }
}
