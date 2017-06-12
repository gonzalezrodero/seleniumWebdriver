package com.seleniumsimplified.Iterrogation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by drn0342 on 06/04/2017.
 */
public class FindElementsTest extends BaseTest {
    private static final String url = "http://www.compendiumdev.co.uk/" + "selenium/find_by_playground.php";
    private int nestedParagraphCount = 0;
    private int paragraphCount = 0;
    private List<WebElement> webElements;
    private WebElement webElement;

    @BeforeClass
    public static void startDriver(){
        driver.get(url);
    }

    @Test
    public void find19DivElementsTest(){
        webElements = driver.findElements(By.tagName("div"));
        assertThat(webElements.size(), is(19));
    }

    @Test
    public void find25aWhichHrefToAParaTest(){
        webElements = driver.findElements(By.partialLinkText("jump to para"));
        assertThat(webElements.size(), is(25));
    }

    @Test
    public void find16NestedParagraphsTest(){
        getNestedParagraphCount();
        assertThat(nestedParagraphCount, is(16));
    }

    @Test
    public void find41ParagraphsInTotal(){
        getNestedParagraphCount();
        getParagraphCount();
        assertThat(paragraphCount + nestedParagraphCount, is(41));
    }


    private void getParagraphCount(){
        webElements = driver.findElements(By.tagName("p"));
        List<WebElement> aWebElements = new ArrayList<WebElement>();
        for (WebElement w : webElements){
            if(w.findElements(By.tagName("a")).size() != 0 && w.getText().contains("paragraph text")) {
                aWebElements.add(w.findElement(By.tagName("a")));
            }
        }
        paragraphCount = aWebElements.size();
    }

    private void getNestedParagraphCount(){
        webElement = driver.findElement(By.className("nestedDiv"));
        nestedParagraphCount++;
        startNestedParagraphCount(webElement);
        getParagraphCount();
    }

    private void startNestedParagraphCount(WebElement w){
        if(w.findElements(By.className("nestedDiv")).size() != 0) {
            webElement = w.findElement(By.className("nestedDiv"));
            nestedParagraphCount++;
            startNestedParagraphCount(webElement);
        }
    }
}
