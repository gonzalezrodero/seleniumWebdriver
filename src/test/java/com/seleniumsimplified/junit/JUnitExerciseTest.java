package com.seleniumsimplified.junit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by danielgonzalezrodero on 31/3/17.
 */
public class JUnitExerciseTest {
    private static String name = "Dani";
    private int nameLength = name.length();

    @BeforeClass
    public static void assignValueToFieldTest(){
        name = "Daniel";
    }

    @Before
    public void setNameLengthTest() {
        nameLength = name.length();
    }

    @Test
    public void isFalseNameIsDaniTest(){
        assertFalse("name should not be dani", name.equalsIgnoreCase("dani"));
    }

    @Test
    public void isTrueNameIsDanielTest(){
        assertTrue("name should be daniel", name.equalsIgnoreCase("daniel"));
    }

    @Test
    public void nameLenghtEquals6Test(){
        assertEquals("name should equal daniel", 6, name.length());
    }

    @Test
    public void assertThatTest(){
        assertThat("the length should be 6", nameLength, is(6));
    }
}
