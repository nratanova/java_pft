package ru.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Наташа on 05.02.2017.
 */
public class EquationsTests {

  @Test
  public void testEq1() {
    Equations e = new Equations(1, 1, 1);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void testEq2() {
    Equations e = new Equations(1, 2, 1);
    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void testEq3() {
    Equations e = new Equations(1, 5, 6);
    Assert.assertEquals(e.rootNumber(),2);
  }

  @Test
  public void testEq4() {
    Equations e = new Equations(0, 5, 6);
    Assert.assertEquals(e.rootNumber(),1);
  }

  @Test
  public void testEq5() {
    Equations e = new Equations(0, 0, 6);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void testEq6() {
    Equations e = new Equations(0, 0, 0);
    Assert.assertEquals(e.rootNumber(),-1);
  }
}
