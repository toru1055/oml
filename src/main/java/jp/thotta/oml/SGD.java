package jp.thotta.oml;

public class SGD implements Learner {
  Weights weights = new WeightsOnMemory();
  double[] w;
  double eta = 0.1;
  double lambda = 0.1;

  public SGD() {}

  public SGD(double lambda) {
    this.lambda = lambda;
  }

  public SGD(double eta, double lambda) {
    this.eta = eta;
    this.lambda = lambda;
  }

  double getProb(Feature[] x) {
    double wx = 0.0;
    for(Feature xi:x) {
      wx += w[xi.hashKey()] * xi.v;
    }
    return sigmoid(wx);
  }

  public int update(Feature[] x, int label) {
    w = weights.get();
    int y = label > 0.5 ? 1 : 0;
    double p = getProb(x);
    for(Feature xi:x) {
      w[xi.hashKey()] -= eta * (p - y) * xi.v;
    }
    weights.set(w);
    return p > 0.5 ? 1 : 0;
  }

  public int predict(Feature[] x) {
    w = weights.get();
    double p = getProb(x);
    return p > 0.5 ? 1 : 0;
  }

  public void init(){ weights.init(); }

  static double sigmoid(double a) {
    return 1 / (1 + Math.exp(-a));
  }
}
