package com.example.gravityandorbits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class VectorTest {

    /*Add these VM options for running tests:

    --add-opens org.junit.platform.commons/org.junit.platform.commons.util=ALL-UNNAMED
    --add-opens org.junit.platform.commons/org.junit.platform.commons.logging=ALL-UNNAMED

     */

    @Test
    void addTest() {
        Vector vector = new Vector(1,1);
        Vector result = vector.add(new Vector(2,2));
        assertEquals(new Vector(3, 3), result);
    }

    @Test
    void subtractTest() {
        Vector vector = new Vector(5,5);
        Vector result = vector.subtract(new Vector(1,1));
        assertEquals(new Vector(4, 4), result);

    }

    @Test
    void multiplyTest() {
        Vector vector = new Vector(3, 3);
        Vector result = vector.multiply(2);
        assertEquals(new Vector(6, 6), result);
    }

    @Test
    void divideTest() {
        Vector vector = new Vector(12, 12);
        Vector result = vector.divide(3);
        assertEquals(new Vector(4, 4), result);
    }

    @Test
    void magnitudeTest() {
        Vector vector = new Vector(3, 4);
        double result = vector.magnitude();
        assertEquals(5, result);
    }

    @Test
    void magnitudeSquaredTest() {
        Vector vector = new Vector(5, 6);
        double result = vector.magnitudeSquared();
        assertEquals(61, result);
    }

    @Test
    void dotTest() {
        Vector vector = new Vector(2, 6);
        double result = vector.dot(new Vector(3, 4));
        assertEquals(30, result);
    }

    @Test
    void distanceToTest() {
        Vector vector = new Vector(3, 5);
        double result = vector.distanceTo(new Vector(4, 4));
        assertEquals(Math.sqrt(2), result);
    }

    @Test
    void toStringTest() {
        Vector vector = new Vector(3.63864, 7.83291);
        String result = vector.toString();
        assertEquals("Vector(3.639, 7.833)", result);
    }

    @Test
    void equalsTest() {
        Vector vector = new Vector(3, 2);
        boolean result = new Vector(2, 6).equals(vector);
        assertFalse(result);
    }
}
