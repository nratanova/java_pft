package ru.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Наташа on 29.01.2017.
 */
public class PointTest {

  @Test
  public void rasstTest() {
    Point p1 = new Point(1,2);
    Point p2 = new Point(4, 7);
    Point p3 = new Point(1,0);
    Assert.assertEquals(p1.distance(4,6), 5.0);
    Assert.assertEquals(p2.distance(8,10), 5.0);
    Assert.assertEquals(p3.distance(1,2), 2.0);


  }
}
