package com.seleniumsimplified.junit;

import org.junit.*;

/**
 * Created by drn0342 on 30/03/2017.
 */
public class JUnitBeforeAndAfterTest {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Before Class test");
    }

    @Before
    public void beforeTest(){
        System.out.println("Before test");
    }

    @Test
    public void this1Test(){
        System.out.println("This is one test");
    }

    @Test
    public void this2Test(){
        System.out.println("This is two test");
    }

    @Test
    public void this3Test(){
        System.out.println("This is three test");
    }

    @After
    public void afterTest(){
        System.out.println("After test");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("After Class test");
    }
}
