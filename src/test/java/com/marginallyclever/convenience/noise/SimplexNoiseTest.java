package com.marginallyclever.convenience.noise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class SimplexNoiseTest {
    private SimplexNoise simplexNoise;

    @BeforeEach
    //Arrange a new instance for each test
    public void beforeEach() {
        simplexNoise = new SimplexNoise();
    }

    @Test
    //Ensure that the result is scaled to return values in the interval [-1,1].
    //Ensure that noise generation is deterministic depending on input
    public void testNoise1D() {
        double noiseValue1 = simplexNoise.noise(Double.MIN_VALUE);
        double noiseValue2 = simplexNoise.noise(Double.MIN_VALUE);
        Assertions.assertTrue(noiseValue1 >= -1 && noiseValue1 <= 1);
        Assertions.assertEquals(noiseValue1, noiseValue2);
    }

    @Test
    //Ensure that the result is scaled to return values in the interval [-1,1].
    //Ensure that noise generation is deterministic depending on input
    public void testNoise2D() {
        double val = Math.random();
        double noiseValue1 = simplexNoise.noise(val, val);
        double noiseValue2 = simplexNoise.noise(val, val);
        Assertions.assertTrue(noiseValue1 >= -1.0 && noiseValue1 <= 1.0);
        Assertions.assertEquals(noiseValue1, noiseValue2);
    }

    @Test
    //Ensure that the result is scaled to return values in the interval [-1,1].
    //Ensure that noise generation is deterministic depending on input
    public  void testNoise3d() {
        double noiseValue1 = simplexNoise.noise(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        double noiseValue2 = simplexNoise.noise(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Assertions.assertTrue(noiseValue1 >= -1.0 && noiseValue1 <= 1.0);
        Assertions.assertEquals(noiseValue1, noiseValue2);
    }

    @Test
    //Good noise generator outputs should be statistically balanced around the center of distribution(0) for
    //big numbers. A normal distribution between -0.5 and 0.5 make sense
    //Ensure that the underlying maths aren't skewed in one direction or the other
    public void testNoiseOutputDistribution() {
        double noise = 0;
        int n = 10000;
        for (int i = 0; i < n; i++) {
            noise += simplexNoise.noise(Math.random(), Math.random());
        }
        double average = noise / n;
        Assertions.assertTrue(average >= -0.5 && average <= 0.5);
    }

}
