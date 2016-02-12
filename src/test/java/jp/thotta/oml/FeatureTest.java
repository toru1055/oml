package jp.thotta.oml;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FeatureTest extends TestCase {
  public void testFeature() {
    Feature a = new Feature(1025, 0.9);
    assertEquals(a.k, 1025);
    assertEquals(a.hashKey(), 1);
    assertEquals(a.v, 0.9, 0.001);
  }
}

