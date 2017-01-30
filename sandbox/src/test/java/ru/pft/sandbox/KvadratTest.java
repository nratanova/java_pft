package ru.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by Наташа on 29.01.2017.
 */
public class KvadratTest {

  @Test
  public void testKv(){
    Kvadrat k = new Kvadrat(5);
    Assert.assertEquals(k.kv(),25.0);

  }
}
