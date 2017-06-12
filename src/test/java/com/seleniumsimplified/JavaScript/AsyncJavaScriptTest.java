package com.seleniumsimplified.JavaScript;

import com.seleniumsimplified.BaseTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by drn0342 on 09/05/2017.
 */
public class AsyncJavaScriptTest extends BaseTest{
    private static URL url;
    private static JavascriptExecutor js;

    @BeforeClass
    public static void setDriver() throws MalformedURLException {
        url = new URL("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
        driver.navigate().to(url);
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Before
    public void refreshPageBeforeEachTest(){
        driver.navigate().refresh();
        driver.manage().window().maximize();
    }

    @Test
    public void waitInBrowserForTimeSample(){
        long start = System.currentTimeMillis();
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        assertTrue("Elapsed time should be greater than 500 milli", (System.currentTimeMillis() - start) > 500);
    }

    @Test
    public void useXMLHttpRequest(){
        Object response = js.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  }" +
                        "};" +
                        "xhr.send();");
        System.out.println((String)response);
        assertThat((String)response, containsString("{optionValue:10, optionDisplay: 'C++'}"));
    }
}
