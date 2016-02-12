package jp.thotta.oml;

public class Instance {
  public int label;
  public Feature[] x;
  public Instance(int label, Feature[] x){
    this.label = label;
    this.x = x;
  }
}
