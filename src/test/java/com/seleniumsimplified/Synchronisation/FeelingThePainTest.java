package com.seleniumsimplified.Synchronisation;

import com.seleniumsimplified.BaseTest;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.CSS;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by drn0342 on 27/04/2017.
 */
public class FeelingThePainTest extends BaseTest{
    private static URL url;
    private static WebDriverWait wait;

    @BeforeClass
    public static void startDriver() throws MalformedURLException{
        url = new URL("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        wait = new WebDriverWait(driver, 10);
    }

    @Before
    public void goToStartPage(){
        driver.navigate().to(url);
    }

    @Test
    public void valueLanguageId23(){
        driver.findElement(By.cssSelector("select[id='combo1']")).findElement(By.cssSelector("option[value='3']")).click();
        wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
        driver.findElement(By.cssSelector("select[id='combo2']")).findElement(By.cssSelector("option[value='23']")).click();
        driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
        wait.until(titleIs("Processed Form Details"));
        assertThat(driver.findElement(By.cssSelector("li[id='_valuelanguage_id")).getText(), is("23"));
    }

    @Test
    public void valueLanguageId23WebElement(){
        driver.findElement(By.cssSelector("select[id='combo1']")).findElement(By.cssSelector("option[value='3']")).click();
        wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
        driver.findElement(By.cssSelector("select[id='combo2']")).findElement(By.cssSelector("option[value='23']")).click();
        driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
        WebElement webElement = wait.until(new TheWebElementExists("li[id='_valuelanguage_id"));
        assertThat(webElement.getText(), is("23"));
    }

    private class TheWebElementExists implements ExpectedCondition<WebElement>{
        String CSSSelector;

        public TheWebElementExists(final String CSSSelector){
            this.CSSSelector = CSSSelector;
        }

        public WebElement apply(WebDriver input) {
            WebElement webElement = input.findElement(By.cssSelector(CSSSelector));
            if(webElement.isDisplayed()){
                return webElement;
            }
            return null;
        }
    }
}