package jp.thotta.oml;

public interface InstanceMaker {
  public void init();
  public boolean hasNext();
  public Instance nextInstance();
}

