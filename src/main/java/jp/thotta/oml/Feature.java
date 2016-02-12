package jp.thotta.oml;

public class Feature {
  static int FEATURE_SIZE = 1024;
  public int k;
  public double v;
  Feature(int k, double v) {
    this.k = k;
    this.v = v;
  }

  public int hashKey() {
    return (k & 0x7FFFFFFF) % FEATURE_SIZE;
  }

  public static int maxSize() {
    return FEATURE_SIZE;
  }
}
