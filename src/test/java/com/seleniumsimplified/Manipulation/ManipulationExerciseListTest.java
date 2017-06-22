package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import com.seleniumsimplified.enums.OperativeSystem;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by drn0342 on 20/04/2017.
 */
public class ManipulationExerciseListTest extends BaseTest{
    private static URL url;
    private WebElement webElement;
    private static String OS;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setDriver() throws MalformedURLException{
        url = new URL("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        driver.navigate().to(url);
        OS = System.getProperty("os.name").toLowerCase();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void pageTitleChanges(){
        String title = driver.getTitle();
        submitForm();
        wait.until(ExpectedConditions.not(titleIs(title)));
        assertThat(driver.getTitle(), is(not(title)));
    }

    @Test
    public void commentsSubmitFormOutput(){
        String comment = "This is a comment for a test";
        wait.until(presenceOfElementLocated(By.cssSelector("textarea[name='comments']")));
        webElement = driver.findElement(By.cssSelector("textarea[name='comments']"));
        webElement.clear();
        webElement.sendKeys(comment);
        submitForm();
        assertThat(driver.findElement(By.cssSelector("li[id='_valuecomments']")).getText(), is(comment));
    }

    @Test
    public void submitFormWithRadio2Selected(){
        driver.findElement(By.cssSelector("input[value='rd1']")).click();
        submitForm();
        wait.until(presenceOfElementLocated(By.cssSelector("li[id='_valueradioval']")));
        assertThat(driver.findElement(By.cssSelector("li[id='_valueradioval']")).getText(), is("rd1"));
    }

    @Test
    public void submitFormWithOneCheckBoxSelected(){
        unselectAllCheckboxes();
        driver.findElement(By.cssSelector("input[value='cb3']")).click();
        submitForm();
        wait.until(presenceOfElementLocated(By.cssSelector("li[id='_valuecheckboxes0']")));
        assertThat(driver.findElement(By.cssSelector("li[id='_valuecheckboxes0']")).getText(), is("cb3"));
    }

    @Test
    public void submitFormWithDropDownItemFiveSelected(){
        wait.until(presenceOfElementLocated(By.cssSelector("option[value='dd5']")));
        driver.findElement(By.cssSelector("option[value='dd5']")).click();
        submitForm();
        wait.until(presenceOfElementLocated(By.cssSelector("li[id='_valuedropdown']")));
        assertThat(driver.findElement(By.cssSelector("li[id='_valuedropdown']")).getText(), is("dd5"));
    }

    @Test
    public void submitFormWithMultipleSelect(){
        //unselectAllMultipleSelectValues();
        wait.until(presenceOfElementLocated(By.cssSelector("select[name='multipleselect[]']")));
        Select oSelect = new Select(driver.findElement(By.cssSelector("select[name='multipleselect[]']")));
        oSelect.deselectAll();
        oSelect.selectByVisibleText("Selection Item 1");
        oSelect.selectByValue("ms2");
        oSelect.selectByIndex(2);
        assertThat(oSelect.getAllSelectedOptions().size(), is(3));
        submitForm();
        wait.until(presenceOfElementLocated(By.cssSelector("li[id*='_valuemultipleselect']")));
        assertThat(driver.findElement(By.cssSelector("li[id='_valuemultipleselect0']")).getText(), is("ms1"));
        assertThat(driver.findElement(By.cssSelector("li[id='_valuemultipleselect1']")).getText(), is("ms2"));
        assertThat(driver.findElement(By.cssSelector("li[id='_valuemultipleselect2']")).getText(), is("ms3"));
    }

    @Test
    public void submitFormWithAFile(){
        if(OS.contains(OperativeSystem.Windows.toString())){
            driver.findElement(By.cssSelector("input[type='file']")).sendKeys("C:\\SpringerNatureProjects\\build-config.json");
            submitForm();
            assertThat(driver.findElement(By.cssSelector("li[id='_valuefilename']")).getText(), is("build-config.json"));
        } else {
            submitForm();
            assertTrue(true);
        }
    }

    @After
    public void goBackToForm(){
        wait.until(presenceOfElementLocated(By.cssSelector("a[id='back_to_form']")));
        driver.findElement(By.cssSelector("a[id='back_to_form']")).click();
        //new WebDriverWait(driver, 2).until(ExpectedConditions.titleIs("HTML Form Elements"));
    }

    private void submitForm(){
        driver.findElement(By.cssSelector("input[value='submit'][name='submitbutton']")).submit();
        //driver.findElement(By.cssSelector("input[value='submit'][name='submitbutton']")).click();
        //driver.findElement(By.cssSelector("input[value='submit'][name='submitbutton']")).sendKeys(Keys.SPACE);
    }

    private void unselectAllCheckboxes(){
        List<WebElement> webElements = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for(WebElement w : webElements){
            if(w.isSelected()){
                w.click();
            }
        }
    }

    private void unselectAllMultipleSelectValues(){
        List<WebElement> webElements = driver.findElements(By.cssSelector("option[value^='ms']"));
        for(WebElement w : webElements) {
            if(w.isSelected()) {
                w.click();
            }
        }
    }
}
