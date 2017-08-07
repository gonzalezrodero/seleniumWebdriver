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
    public static final String BROWSER = "selenium2basics.driver";
    private static final File PHANTOMJS = new File("tools/phantomjs-2.1.1-macosx/bin/phantomjs");
    public static String  browserToUse = "";
    private static WebDriver webDriver;

    public static void setWebDriver() throws Exception {
        if(webDriver == null) {
            //setDefaultBrowser();

            switch (System.getProperty(BROWSER)) {
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", "webdrivers-win/chrome/chromedriver.exe");
                    webDriver = new ChromeDriver();
                    break;
                case "FIREFOX":
                    System.setProperty("webdriver.gecko.driver", "webdrivers-win/geckodriver/geckodriver.exe");
                    DesiredCapabilities ffCaps = DesiredCapabilities.firefox();
                    ffCaps.setCapability(CapabilityType.TAKES_SCREENSHOT, Boolean.TRUE);
                    webDriver = new FirefoxDriver(ffCaps);
                    break;
                case "IE":
                    DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
                    ieCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, Boolean.TRUE);
                    webDriver = new InternetExplorerDriver(ieCaps);
                    break;
                case "HTMLUNIT":
                    webDriver = new HtmlUnitDriver();
                    break;
                case "GHOST":
                    DesiredCapabilities phantomCaps = DesiredCapabilities.phantomjs();
                    phantomCaps.setJavascriptEnabled(true);
                    phantomCaps.setCapability("phantomjs.binary.path", PHANTOMJS.getAbsolutePath());
                    webDriver = new PhantomJSDriver(phantomCaps);
                    break;
                case "SAUCE_LABS":
                    webDriver = getSauceRemoteWebDriver();
                    break;
                case "APPIUM":
                    startAppiumServer();
                    webDriver = getAppiumRemoteWebDriver();
                    break;
                default:
                    webDriver = new ChromeDriver();
            }

        }
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public static void quitWebDriver() throws InterruptedException, IOException {
        Runtime.getRuntime().addShutdownHook(
                new Thread(){
                    public void run(){
                        webDriver.quit();
                    }
                }
        );
        if (browserToUse.equals("APPIUM")){
            stopAppiumServer();
        }
    }

    public static void close(){
        webDriver.close();
    }

    private static void setDefaultBrowser() {
        if (System.getProperty(BROWSER) == null) {
            System.setProperty(BROWSER, "FIREFOX");
        }
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