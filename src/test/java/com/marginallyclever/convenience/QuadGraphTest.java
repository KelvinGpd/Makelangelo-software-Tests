package com.marginallyclever.convenience;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.awt.geom.Rectangle2D;

public class QuadGraphTest {
    private QuadGraph quadGraph;

    @BeforeEach
    //Arrange a 10x10 graph to use for tests
    public void beforeEach() {
        quadGraph = new QuadGraph(0, 0, 10, 10);;
    }

    @Test
    //Make sure the graph attributes are correctly assigned
    public void testInitQuadGraph() {
        Assert.assertEquals(0, quadGraph.sites.size());
        Assert.assertNull(quadGraph.children);
        Assert.assertEquals(new Rectangle2D.Double(0, 0, 10, 10), quadGraph.bounds);
    }

    @Test
    //Insert a point inside the graph and make sure
    //it is added
    public void testInsertInside() {
        Point2D point2D = new Point2D(5, 5);
        Assert.assertTrue(quadGraph.insert(point2D));
        Assert.assertEquals(1, quadGraph.sites.size());
    }

    @Test
    //Insert a point outside the graph area and
    //verify it isn't added
    public void testInsertOutside() {
        Point2D point2D = new Point2D(11, 11);
        Assert.assertFalse(quadGraph.insert(point2D));
        Assert.assertEquals(0, quadGraph.sites.size());
    }

    @Test
    //Overfill MAX_POINTS and trigger split()
    //verify split into 4 children
    public void testSplit() {
        for (int i=0; i < 5; i++) {
            quadGraph.insert(new Point2D(i, i));
        }
        quadGraph.insert(new Point2D(6, 6));
        Assert.assertNotNull(quadGraph.children);
        Assert.assertEquals(4, quadGraph.children.length);
    }

    @Test
    //Search for the closest point inside the graph, given two existing points
    //validate for different distances
    public void testSearchInside() {
        Point2D p1 = new Point2D(2,2);
        Point2D p2 = new Point2D(6,6);

        quadGraph.insert(p1);
        quadGraph.insert(p2);

        //closer to p1
        Assert.assertEquals(p1, quadGraph.search(new Point2D(3, 3)));
        //closer to p2
        Assert.assertEquals(p2, quadGraph.search(new Point2D(5, 5)));
        //exact match
        Assert.assertEquals(p1, quadGraph.search(new Point2D(2, 2)));
        //equal distance, returns first
        Assert.assertEquals(p1, quadGraph.search(new Point2D(4, 4)));
    }

    @Test
    //Search for the closest point outside the graph
    //ensure that the result is Null
    public void testSearchOutside() {
        Point2D pointOut = new Point2D(11, 11);
        Assert.assertNull(quadGraph.search(pointOut));
    }

    @Test
    //Search for the closest points inside the children
    //ensure that the result point coordinates are equivalent
    public void testSearchInChildren() {
        for (int i = 0; i < 5; i++) {
            quadGraph.insert(new Point2D(i, i));
        }
        quadGraph.insert(new Point2D(6, 6));

        Point2D pInChild1 = new Point2D(1, 1);
        Point2D pInChild2 = new Point2D(7, 7);

        quadGraph.insert(pInChild1);
        quadGraph.insert(pInChild2);

        Point2D r1 = quadGraph.search(new Point2D(1,1));
        Point2D r2 = quadGraph.search(new Point2D(7, 7));

        Assert.assertEquals(pInChild1.x, r1.x, 0);
        Assert.assertEquals(pInChild1.y, r1.y, 0);
        Assert.assertEquals(pInChild2.x, r2.x, 0);
        Assert.assertEquals(pInChild2.y, r2.y, 0);
    }
}
