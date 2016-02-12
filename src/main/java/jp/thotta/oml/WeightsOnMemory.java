package jp.thotta.oml;

public class WeightsOnMemory implements Weights {
  double[] w;

  public WeightsOnMemory() {
    init();
  }
  
  public void init() {
    w = new double[Feature.maxSize()];
    for(int i = 0; i < w.length; i++) {
      w[i] = Math.random();
    }
  }

  public void set(double[] w) {
    for(int i = 0; i < w.length; i++) {
      this.w[i] = w[i];
    }
  }

  public double[] get() {
    return this.w;
  }
}
