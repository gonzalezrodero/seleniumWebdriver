package com.seleniumsimplified.JavaScript;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by danielgonzalezrodero on 8/5/17.
 */
public class JavaScriptTest extends BaseTest{
    private static URL url;
    private static JavascriptExecutor js;

    /*@BeforeClass
    public static void setDriver() throws MalformedURLException{
        url = new URL("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");
        driver.navigate().to(url);
        js = (JavascriptExecutor) driver;
    }

    @Before
    public void refreshPageBeforeEachTest(){
        driver.navigate().refresh();
        driver.manage().window().maximize();
    }

    @Test
    public void draw(){
        List<WebElement> actions = driver.findElement(By.cssSelector("ul#commandlist")).findElements(By.cssSelector("li"));
        assertThat(actions.size(), is(2));
        js.executeScript("draw(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);", 1, 50, 50, 40, "#FF0000");
        actions = driver.findElement(By.cssSelector("ul#commandlist")).findElements(By.cssSelector("li"));
        assertThat(actions.size(), is(3));
        WebElement drew = null;
        for (WebElement action : actions){
            if(action.getText().contains("draw(1,50,50,40,#FF0000)")){
                drew = action;
            }
        }
        //assertThat(drew.getText(), containsString("draw(1,50,50,40,#FF0000)"));
        assertThat(drew, is(not(nullValue())));
    }

    @Test
    public void twoArgumentsReturnResult(){
        Object result = js.executeScript("return arguments[0] + arguments[1];", 3, 9);
        assertThat((Long)result, is(12L));
    }

    @Test
    public void hideElementJQuery(){
        WebElement button = driver.findElement(By.cssSelector("input[name='gobutton']"));
        assertTrue(button.isDisplayed());
        js.executeScript("$(arguments[0]).hide()", button);
        assertFalse(button.isDisplayed());
    }

    @Test
    public void jsFunctionLeftBehind(){
        js.executeScript("alert('alert triggered by webdriver');");
        assertThat(driver.switchTo().alert().getText(), is("alert triggered by webdriver"));
        driver.switchTo().alert().accept();
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');}");
        js.executeScript("window.webdriveralert.call();");
        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();
    }*/
}
