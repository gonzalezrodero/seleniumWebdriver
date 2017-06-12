package com.seleniumsimplified.Manipulation;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Created by drn0342 on 25/04/2017.
 */
public class ManageWindowExerciseListTest extends BaseTest{
    private static URL url;
    private java.awt.Dimension fullScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    @BeforeClass
    public static void startDriver() throws MalformedURLException{
        url = new URL("http://www.compendiumdev.co.uk/selenium/bounce.html");
        driver.navigate().to(url);
    }

    @Test
    public void maximizeWindow() throws InterruptedException {
        driver.manage().window().maximize();
        Dimension windowSizeMax = driver.manage().window().getSize();

        assertThat(windowSizeMax.getHeight(), greaterThan((int) (fullScreenSize.getHeight()*0.75)));
        assertThat(windowSizeMax.getWidth(), greaterThan((int) (fullScreenSize.getWidth()*0.75)));
    }

    @Test
    public void reduceWindowHalfSize(){
        driver.manage().window().maximize();
        Dimension windowSizeMax = driver.manage().window().getSize();
        driver.manage().window().setSize(new Dimension(windowSizeMax.getWidth()/2, windowSizeMax.getHeight()/2));

        Dimension windowSize = driver.manage().window().getSize();
        driver.manage().window().setPosition(new Point(windowSize.getWidth()/2, windowSize.getHeight()/2));
        bounce(driver.manage().window().getPosition(), windowSizeMax);
    }

    private void bounce(Point windowLocation, Dimension screenSize){
        Dimension windowSize = new Dimension(driver.manage().window().getSize().getWidth(), driver.manage().window().getSize().getHeight());
        driver.manage().window().setPosition(new Point(windowLocation.getX(),0));
        driver.manage().window().setPosition(new Point(0,windowLocation.getY()));
        driver.manage().window().setPosition(new Point(windowLocation.getX(),screenSize.getHeight() - windowSize.getHeight()));
        driver.manage().window().setPosition(new Point(screenSize.getWidth() - windowSize.getWidth(), windowLocation.getY()));
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setPosition(new Point(screenSize.getWidth() - windowSize.getWidth(),0));
        driver.manage().window().setPosition(new Point(0,screenSize.getHeight() - windowSize.getHeight()));
        driver.manage().window().setPosition(new Point(screenSize.getWidth() - windowSize.getWidth(),screenSize.getHeight() - windowSize.getHeight()));
    }
}
