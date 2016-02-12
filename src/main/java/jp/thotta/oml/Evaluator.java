package jp.thotta.oml;

public class Evaluator {
  int tp, tn, fp, fn;
  public Evaluator() {
    tp = 0; tn = 0; fp = 0; fn = 0;
  }

  public void add(int expected, int predicted) {
    if(predicted == 1) {
      if(expected == 1) {
        tp++;
      } else {
        fp++;
      }
    } else {
      if(expected == 1) {
        fn++;
      } else {
        tn++;
      }
    }
  }

  public double getPrecision() {
    return (double)tp / (tp + fp);
  }

  public double getRecall() {
    return (double)tp / (tp + fn);
  }

  public double getFmeasure() {
    double r = getRecall();
    double p = getPrecision();
    return (2*r*p) / (r+p);
  }

  public double getAccuracy() {
    return (double)(tp+tn) / (tp+fp+fn+tn);
  }

  public void printAll(String msg) {
    System.out.println(msg);
    System.out.println("precision: " + getPrecision());
    System.out.println("recall: " + getRecall());
    System.out.println("f_measure: " + getFmeasure());
    System.out.println("accuracy: " + getAccuracy());
  }
}
