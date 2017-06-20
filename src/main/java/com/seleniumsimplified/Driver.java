package com.seleniumsimplified;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.seleniumsimplified.Appium.*;
import static com.seleniumsimplified.PropertyManager.*;

public class Driver {
    static final String BROWSER_PROPERTY_NAME = "selenium2basics.driver";
    public static String  browserToUse = "";
    private static final File PHANTOMJS_EXE =
            new File(System.getProperty("user.dir"), "tools/phantomjs-2.1.1-macosx/bin/phantomjs");
    private static WebDriver webDriver;

    public static WebDriver get() throws Exception {
        //if(System.getProperties().containsKey(SELENIUM2_BASICS_DRIVER)){
                    //System.getProperty(SELENIUM2_BASICS_DRIVER);
        //}
        if(webDriver != null){
            return webDriver;
        }
        browserToUse = System.getProperty("selenium2basics.driver");
        switch (browserToUse) {
            case "CHROME":
                //System.setProperty("webdriver.chrome.driver", "webdrivers/chrome/chromedriver");
                return webDriver = new ChromeDriver();
            case "Firefox":
                DesiredCapabilities ffCaps = DesiredCapabilities.firefox();
                ffCaps.setCapability(CapabilityType.TAKES_SCREENSHOT, Boolean.TRUE);
                return webDriver = new FirefoxDriver(ffCaps);
            case "IE":
                DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
                ieCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, Boolean.TRUE);
                return webDriver = new InternetExplorerDriver(ieCaps);
            case "HTMLUNIT":
                return webDriver = new HtmlUnitDriver();
            case "GHOST":
                DesiredCapabilities phantomCaps = DesiredCapabilities.phantomjs();
                phantomCaps.setJavascriptEnabled(true);
                phantomCaps.setCapability("phantomjs.binary.path", PHANTOMJS_EXE.getAbsolutePath());
                return webDriver = new PhantomJSDriver(phantomCaps);
            case "SAUCE_LABS":
                return webDriver = getSauceRemoteWebDriver();
            case "APPIUM":
                startAppiumServer();
                return webDriver = getAppiumRemoteWebDriver();
            default:
                return webDriver = new ChromeDriver();
        }
    }

    static void quit() throws InterruptedException, IOException {
        webDriver.quit();
        if (browserToUse.equals("APPIUM")){
            stopAppiumServer();
        }
    }

    public static void close(){
        webDriver.close();
    }

    private static WebDriver getSauceRemoteWebDriver() throws IOException {
        DesiredCapabilities sauceCaps = DesiredCapabilities.firefox();
        sauceCaps.setCapability("version", "5");
        sauceCaps.setCapability("platform", Platform.XP);
        return new RemoteWebDriver(new URL(getProperty("url.saucelabs")), sauceCaps);
    }

    private static WebDriver getAppiumRemoteWebDriver() throws IOException {
        setAppiumCapabilities();
        return new RemoteWebDriver(new URL(getProperty("url.appium")), getAppiumCapabilities());
    }
}