package com.seleniumsimplified.junit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by drn0342 on 30/03/2017.
 */
public class JUnitExampleTest {

    @Test
    public void twoPlusTwoEqualsFour(){
        assertEquals("2+2+4", 4, 2+2);
    }
}
