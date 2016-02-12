package jp.thotta.oml;

public interface Learner {
  public int update(Feature[] x, int label);
  public int predict(Feature[] x);
  public void init();
}
