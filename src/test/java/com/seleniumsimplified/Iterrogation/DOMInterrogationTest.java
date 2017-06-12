package com.seleniumsimplified.Iterrogation;

import com.seleniumsimplified.BaseTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DOMInterrogationTest extends BaseTest{
    private static final String url = "http://www.compendiumdev.co.uk/" + "selenium/find_by_playground.php";
    private WebElement webElement;

    @BeforeClass
    public static void startDriver(){
        driver.get(url);
    }

    @Test
    public void byIdT2est(){
        webElement = driver.findElement(By.id("a8"));
        assertThat(webElement.getAttribute("class"), startsWith("nor"));
    }

    @Test
    public void byLinkTextTest(){
        webElement = driver.findElement(By.linkText("jump to para 3"));
        assertThat(webElement.getAttribute("href"), containsString("//www.compe"));
    }

    @Test
    public void byNameTest(){
        webElement = driver.findElement(By.name("pName20"));
        assertThat(webElement.getAttribute("id"), endsWith("0"));
    }

    @Test
    public void byTagNameTest(){
        webElement = driver.findElement(By.tagName("p")).findElement(By.tagName("a"));
        assertThat(webElement.getAttribute("name"), is("pName1"));
    }

    @Test
    public void byPartialLinkTextTest() {
        webElement = driver.findElement(By.partialLinkText("para 0"));
        assertThat(webElement.getText(), is(not("jump to para 1")));
    }

    @Test
    public void byClassNameTest(){
        webElement = driver.findElement(By.className("nestedDiv"));
        assertThat(webElement.getTagName(), is("div"));
    }

    @Test
    public void noSuchElementException_thrownWhenLocatorWrongTest(){
        try {
            webElement = driver.findElement(By.id("p3Name"));
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e){}
    }

    @Test(expected = NoSuchElementException.class)
    public void noSuchElementException_thrownWhenLocatorWrong_ExpectedTest(){
        webElement = driver.findElement(By.id("p3Name"));
    }
}
