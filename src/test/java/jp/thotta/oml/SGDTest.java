package jp.thotta.oml;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SGDTest extends TestCase {
  public void testNormal() {
    SGD sgd = new SGD(0.3);
    Feature[] x0 = new Feature[3];
    x0[0] = new Feature(0, 0.0);
    x0[1] = new Feature(1, 0.9);
    x0[2] = new Feature(2, 0.0);
    Feature[] x1 = new Feature[3];
    x1[0] = new Feature(0, 0.9);
    x1[1] = new Feature(1, 0.0);
    x1[2] = new Feature(2, 0.9);
    for(int i = 0; i < 100; i++) {
      sgd.update(x0, 0);
      sgd.update(x1, 1);
    }
    System.out.println("x0(0): " + sgd.predict(x0));
    System.out.println("x1(1): " + sgd.predict(x1));
    assertEquals(0, sgd.predict(x0));
    assertEquals(1, sgd.predict(x1));
  }
}
