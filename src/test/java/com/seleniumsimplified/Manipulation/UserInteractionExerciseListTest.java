package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * Created by drn0342 on 20/04/2017.
 */
public class UserInteractionExerciseListTest extends BaseTest{
    private static URL url;

    @BeforeClass
    public static void startDriver() throws MalformedURLException{
        url = new URL("http://compendiumdev.co.uk/selenium/gui_user_interactions.html");
        driver.navigate().to(url);
    }

    @Before
    public void refreshPage(){
        driver.navigate().refresh();
    }

    @Test
    public void moveDraggable1OnToDroppable1(){
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.cssSelector("div[id='draggable1']")))
                .moveToElement(driver.findElement(By.cssSelector("div[id='droppable1']")))
                .release()
                .perform();
        assertThat(driver.findElement(By.cssSelector("div[id='droppable1']")).getText(), is("Dropped!"));
    }

    @Test
    public void dragAndDropDraggable2ToDroppable1(){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(By.cssSelector("div[id='draggable2']")),
            driver.findElement(By.cssSelector("div[id='droppable1']")))
                .release()
                .perform();
        assertThat(driver.findElement(By.cssSelector("div[id='droppable1']")).getText(), is("Get Off Me!"));
    }

    @Test
    public void pressControlB(){
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .sendKeys("b")
                .keyUp(Keys.CONTROL)
                .perform();
        assertThat(driver.findElement(By.cssSelector("div[id='draggable1']")).getText(), is("Bwa! Ha! Ha!"));
    }

//    @Test
//    public void pressControlSpace(){
//        Actions actions = new Actions(driver);
//        actions.keyDown(Keys.CONTROL)
//                .keyDown(Keys.SPACE)
//                .perform();
//        assertThat(driver.findElement(By.cssSelector("div[id='droppable1']")).getText(), is("Let GO!!!"));
//    }

    @Test
    public void drawSomething(){
        Actions actions = new Actions(driver);
        int eventCount = driver.findElements(By.cssSelector("li")).size();
        actions.clickAndHold(driver.findElement(By.cssSelector("canvas[id='canvas']")))
                .moveByOffset(10,10)
                .release()
                .perform();
        System.out.println();
        assertThat(driver.findElements(By.cssSelector("li")).size(), greaterThan(eventCount));
    }
}
