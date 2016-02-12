package jp.thotta.oml;

public class PA1 implements Learner {
  Weights weights = new WeightsOnMemory();
  double[] w;
  double eta = 1.0;
  double lambda = 0.1;

  public PA1() {}

  public PA1(double lambda) {
    this.lambda = lambda;
  }

  public PA1(double eta, double lambda) {
    this.eta = eta;
    this.lambda = lambda;
  }


  double getMargin(Feature[] x) {
    double wx = 0.0;
    for(Feature xi:x) {
      wx += w[xi.hashKey()] * xi.v;
    }
    return wx;
  }

  public int update(Feature[] x, int label) {
    w = weights.get();
    int y = label > 0.0 ? 1 : -1;
    double wx = getMargin(x);
    double loss = Math.max(0, 1.0 - y * wx);
    double coef = Math.min(1/lambda, loss/norm2(x));
    for(Feature xi:x) {
      w[xi.hashKey()] += eta * y * coef * xi.v;
    }
    weights.set(w);
    return wx > 0.0 ? 1 : 0;
  }

  public int predict(Feature[] x) {
    w = weights.get();
    double m = getMargin(x);
    return m > 0.0 ? 1 : 0;
  }

  public void init(){ weights.init(); }

  static double norm2(Feature[] x) {
    double norm = 0.0;
    for(Feature xi:x) {
      norm += xi.v * xi.v;
    }
    return norm;
  }
}
