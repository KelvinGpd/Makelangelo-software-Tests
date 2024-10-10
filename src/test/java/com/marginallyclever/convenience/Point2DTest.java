package com.marginallyclever.convenience;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class Point2DTest
{
    private Point2D p;

    @BeforeEach
    //Arrange a new instance for each test
    public void beforeEach() {
        p = new Point2D();
    }

    @Test
    //Normalizes the length of a 2D point
    //Ensure that the normalization is correct
    public void testNormalize()
    {
        Point2D pTest = new Point2D(2, 4);
        Point2D pTest0 = new Point2D(0, 0);
        Point2D p0 = new Point2D(0, 0);
        p.set(2, 4);
        p.normalize();
        p0.normalize();
        Assertions.assertNotEquals(p.x, pTest.x);
        Assertions.assertNotEquals(p.y, pTest.y);
        Assertions.assertEquals(p.x, (pTest.x)/pTest.length());
        Assertions.assertEquals(p.y, (pTest.y)/pTest.length());
        //In case of length = 0, normalization just isn't possible, and the two points should stay equal
        Assertions.assertEquals(p0.x, pTest0.x);
        Assertions.assertEquals(p0.y, pTest0.y);
    }

    @Test
    //Simply adds the coordinates of 2 points together
    //Ensure that the process is correct.
    public void testAdd()
    {
        p.set(5, 5);
        Point2D p2 = new Point2D(1, 7);
        Point2D pTest = new Point2D(p);
        Point2D pTest2 = new Point2D(0, 0);

        p.add(pTest);
        p2.add(pTest2);
        Assertions.assertEquals(p.x , 10);
        Assertions.assertEquals(p.y , 10);
        Assertions.assertEquals(p2.y , (pTest2.y + p2.y));
        Assertions.assertEquals(p2.x , (pTest2.x + p2.x));
    }

    @Test
    //Simply rescales the value of a 2Dpoint's coordinates
    //Ensure that the scaling goes both ways.
    public void testScale()
    {
        p.set(5,5);
        p.scale(-4);
        Assertions.assertEquals(p.x , -20);
        Assertions.assertEquals(p.y , -20);

        p.scale(-1);
        Assertions.assertEquals(p.x , 20);
        Assertions.assertEquals(p.y , 20);
    }

}