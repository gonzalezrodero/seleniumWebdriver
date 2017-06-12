package com.seleniumsimplified;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.seleniumsimplified.PropertyManager.getProperty;

public class Appium {
    private static AppiumDriverLocalService appiumDriverLocalService;
    private static DesiredCapabilities appiumCaps;

    public static Boolean useAppium() throws IOException {
        return Boolean.valueOf(getProperty("driver.appium"));
    }

    static void setAppiumCapabilities() throws IOException {
        appiumCaps = new DesiredCapabilities();
        appiumCaps.setCapability("deviceName", getProperty("appium.deviceName"));
        appiumCaps.setCapability("platformName", Platform.ANDROID);
        appiumCaps.setCapability("browserName", getProperty("appium.browser"));
    }

    static DesiredCapabilities getAppiumCapabilities() throws IOException {
        return appiumCaps;
    }

    static void startAppiumServer() throws MalformedURLException {
        appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
        appiumDriverLocalService.start();
    }

    static void stopAppiumServer() {
        if(appiumDriverLocalService == null){
            System.out.println("Appium not used in this test run");
        } else {
            appiumDriverLocalService.stop();
        }
    }
}
