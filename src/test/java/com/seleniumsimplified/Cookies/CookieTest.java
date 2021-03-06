package com.seleniumsimplified.Cookies;

import com.seleniumsimplified.BaseTest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CookieTest extends BaseTest{
    private static URL url;

    @BeforeClass
    public static void setDriver() throws MalformedURLException{
        url = new URL("http://compendiumdev.co.uk/selenium/search.php");
        driver.navigate().to(url);
    }

    @Before
    public void beforeTest(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test
    public void firstVisitTest(){
        Cookie cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertThat(cookie.getValue(), is("1"));
    }

    @Test
    public void modifyCookieNumVisitsAndSearchCloning(){
        Cookie cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        driver.manage().deleteCookie(cookie);
        driver.manage().addCookie(
            new Cookie.Builder(
                    cookie.getName(), "42")
                .domain(cookie.getDomain())
                .expiresOn(cookie.getExpiry())
                .isSecure(cookie.isSecure())
                .path(cookie.getPath())
                .build()
        );
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        cookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertThat(cookie.getValue(), is(greaterThan("41")));
    }
}
