package com.example.haoyup.potandweight;

import org.junit.Test;

import static org.junit.Assert.*;


public class PotTest {

    private Pot pot = new Pot("Pan", 10);

    @Test
    public void testGetWeightInG() throws Exception {
        assertEquals(10, pot.getWeightInG());
    }

    @Test
    public void testSetWeightInG() throws Exception {
        pot.setWeightInG(20);
        assertEquals(20, pot.getWeightInG());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Pan", pot.getName());
    }

    @Test
    public void testSetName() throws Exception {
        pot.setName("Big Pan");
        assertEquals("Big Pan", pot.getName());
    }

}