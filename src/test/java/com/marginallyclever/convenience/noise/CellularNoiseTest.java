package com.marginallyclever.convenience.noise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CellularNoiseTest
{
    private CellularNoise cellularNoise;

    @BeforeEach
    //Arrange a new instance for each test
    public void beforeEach() {
        cellularNoise = new CellularNoise();
    }

    @Test
    //Ensure that the result is scaled to return values in the interval [-1,1].
    //Ensure that noise generation is deterministic depending on input
    public void testNoise1D()
    {
        double noiseValue1 = cellularNoise.noise(Double.MIN_VALUE);
        double noiseValue2 = cellularNoise.noise(Double.MIN_VALUE);
        Assertions.assertTrue(noiseValue1 >= -1 && noiseValue1 <= 1);
        Assertions.assertEquals(noiseValue1, noiseValue2);
    }

    @Test
    //Ensure that the result is scaled to return values in the interval [-1,1].
    //Ensure that noise generation is deterministic depending on input
    public void testNoise2D()
    {
        double val = Math.random();
        double noiseValue1 = cellularNoise.noise(val, val);
        double noiseValue2 = cellularNoise.noise(val, val);
        Assertions.assertTrue(noiseValue1 >= -1.0 && noiseValue1 <= 1.0);
        Assertions.assertEquals(noiseValue1, noiseValue2);
    }

    @Test
    //This one works differently, it searches for the noise as the minimal distance at a specific location
    //which is determined by the three coordinates given as arguments.
    public void testNoise3d()
    {
        double[] coords = new double[6];
        for (int i = 0; i < 6; i++)
        {
            coords[i] = Math.random();
        }

        double noiseValue1 = cellularNoise.noise(coords[0], coords[1], coords[2]);
        double noiseValue2 = cellularNoise.noise(coords[3], coords[4], coords[5]);
        Assertions.assertTrue(noiseValue1 >= -1.0 && noiseValue1 <= 1.0);
        Assertions.assertNotEquals(noiseValue1, noiseValue2);
    }
}
