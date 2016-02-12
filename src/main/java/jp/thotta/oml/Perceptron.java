package jp.thotta.oml;

public class Perceptron implements Learner {
  Weights weights = new WeightsOnMemory();
  double[] w;
  double eta = 0.1;
  double lambda = 0.1;

  public Perceptron() {}

  public Perceptron(double lambda) {
    this.lambda = lambda;
  }

  public Perceptron(double eta, double lambda) {
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
    int y = label > 0.5 ? 1 : -1;
    double wx = getMargin(x);
    if(y * wx < 0) {
      for(Feature xi:x) {
        w[xi.hashKey()] += eta * y * xi.v;
      }
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
}
