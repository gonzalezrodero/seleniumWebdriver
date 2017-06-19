package com.seleniumsimplified.ScreenshotTest;

import org.apache.commons.codec.binary.Base64;

import com.seleniumsimplified.BaseTest;
import com.seleniumsimplified.Driver;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TakeScreenshotTest extends BaseTest {
    private static final String DIRECTORY = "./screenshots";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static URL url;
    private static TakesScreenshot snapper;
    private File myScreenshot;

    @BeforeClass
    public static void goToUrl() throws MalformedURLException {
        url = new URL("http://www.google.com");
        driver.navigate().to(url);

    }

    @Test
    public void screenShotCapability() throws IOException {
        checkIfDriverSupportsScreenshot();
        snapper = (TakesScreenshot) driver;
        File tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);
        takeSnapshot(tempScreenshot);
        assertThat(myScreenshot.length(), is(greaterThan(0L)));
    }

    @Test
    public void castingScreenshotToFileWithRandomName() throws IOException{
        File tempScreenshot;
        try{
            snapper = (TakesScreenshot) driver;
            tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);
            takeSnapshot(tempScreenshot);
            assertThat(myScreenshot.length(), is(greaterThan(0L)));
        } catch (ClassCastException e){
            System.out.println(e);
            fail("Driver does not support screenshots");
        }
    }

    @Test
    public void castingScreenshotToBASE64WithRandomName() throws IOException{
        String tempScreenshot;
        try{
            snapper = (TakesScreenshot) driver;
            tempScreenshot = snapper.getScreenshotAs(OutputType.BASE64);
            takeSnapshot(Base64.decodeBase64(tempScreenshot));
            assertThat(myScreenshot.length(), is(greaterThan(0L)));
        } catch (ClassCastException e){
            System.out.println(e);
            fail("Driver does not support screenshots");
        }
    }

    @Test
    public void castingScreenshotToByteWithRandomName() throws IOException{
        byte[] tempScreenshot;
        try{
            snapper = (TakesScreenshot) driver;
            tempScreenshot = snapper.getScreenshotAs(OutputType.BYTES);
            takeSnapshot(tempScreenshot);
            assertThat(myScreenshot.length(), is(greaterThan(0L)));
        } catch (ClassCastException e){
            System.out.println(e);
            fail("Driver does not support screenshots");
        }
    }

    private String getRandomString(){
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        for(int characterPosition = 0; characterPosition < 15; characterPosition++){
            stringBuilder.append(
                    CHARACTERS.charAt(rnd.nextInt(CHARACTERS.length()))
            );
        }
        return stringBuilder.toString();
    }

    private void checkIfDriverSupportsScreenshot(){
        if(!((HasCapabilities) driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
            fail("Driver does not support screenshots");
        }
    }

    private File getDirectoryFile(String directory){
        File fileDirectory = new File(directory);
        if(!fileDirectory.exists()){
            fileDirectory.mkdirs();
        }
        return fileDirectory;
    }

    private void takeSnapshot(File tempScreenshot) throws IOException {
        myScreenshot = createSnapshotFile();
        if(myScreenshot.exists()){
            myScreenshot.delete();
        }
        FileUtils.moveFile(tempScreenshot, myScreenshot);
    }

    private void takeSnapshot(byte[] decodedScreenshot) throws IOException {
        myScreenshot = createSnapshotFile();
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(myScreenshot);
        fileOutputStream.write(decodedScreenshot);
        fileOutputStream.close();
    }

    private File createSnapshotFile(){
        return new File(getDirectoryFile(DIRECTORY), Driver.browserToUse.toUpperCase() + "-" + getRandomString() + ".png");
    }
}
