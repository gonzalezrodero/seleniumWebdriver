package com.seleniumsimplified.Iterrogation;

import com.seleniumsimplified.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ByCSSTest extends BaseTest {
    private static final String url = "http://www.compendiumdev.co.uk/" + "selenium/find_by_playground.php";
    private WebElement webElement;

    @BeforeClass
    public static void startDriver() throws IOException {
        driver.get(url);
    }

    @Test
    public void getNameOfp31(){
        //webElement = driver.findElement(By.id("p31"));
        webElement = driver.findElement(By.cssSelector("#p31"));
        assertThat(webElement.getAttribute("name"), is("pName31"));
    }

    @Test
    public void getIdOfUlName1(){
        //webElement = driver.findElement(By.name("ulName1"));
        webElement = driver.findElement(By.cssSelector("[name='ulName1']"));
        assertThat(webElement.getAttribute("id"), is("ul1"));
    }

    @Test
    public void getIdOfSpecialDiv(){
        //webElement = driver.findElement(By.className("specialDiv"));
        webElement = driver.findElement(By.cssSelector(".specialDiv"));
        assertThat(webElement.getAttribute("id"), is("div1"));
    }

    @Test
    public void getNameOfLi(){
        //webElement = driver.findElement(By.tagName("li"));
        webElement = driver.findElement(By.cssSelector("li"));
        assertThat(webElement.getAttribute("name"), is("liName1"));
    }
}
