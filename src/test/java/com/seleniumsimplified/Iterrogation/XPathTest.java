package com.seleniumsimplified.Iterrogation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by drn0342 on 07/04/2017.
 */
public class XPathTest extends BaseTest{
    private static final String url = "http://www.compendiumdev.co.uk/" + "selenium/find_by_playground.php";
    private WebElement webElement;

    @BeforeClass
    public static void startDriver(){
        driver.get(url);
    }

    @Test
    public void getAttributePNAME31(){
        webElement = driver.findElement(By.xpath("//*[@name='pName31']"));
        assertThat(webElement.getAttribute("name"), is("pName31"));
    }

    @Test
    public void getAttributeUL1(){
        webElement = driver.findElement(By.xpath("//*[@id='ul1']"));
        assertThat(webElement.getAttribute("id"), is("ul1"));
    }

    @Test
    public void getAttributeDIV1(){
        webElement = driver.findElement(By.xpath("//*[@class='specialDiv']"));
        assertThat(webElement.getAttribute("id"), is("div1"));
    }

    @Test
    public void getAttributeLINAME1(){
        webElement = driver.findElement(By.xpath("//li[@name='liName1']"));
        assertThat(webElement.getAttribute("name"), is("liName1"));
    }
}
