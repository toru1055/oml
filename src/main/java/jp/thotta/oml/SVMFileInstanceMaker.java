package jp.thotta.oml;

import java.io.File;
import java.util.Scanner;

public class SVMFileInstanceMaker implements InstanceMaker {
  Scanner sc;
  File file;

  public SVMFileInstanceMaker(File f) {
    this.file = f;
    init();
  }

  public void init() {
    try {
      sc = new Scanner(file);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public boolean hasNext() {
    return sc.hasNext();
  }

  public Instance nextInstance() {
    String line = sc.nextLine();
    String arr[] = line.split(" ");
    int label = arr[0].charAt(0) == '+' ? 1 : 0;
    Feature x[] = new Feature[arr.length - 1];
    for(int i = 0; i < x.length; i++) {
      String[] elem = arr[i+1].split(":");
      int k = Integer.parseInt(elem[0]);
      double v = Double.parseDouble(elem[1]);
      x[i] = new Feature(k, v);
    }
    return new Instance(label, x);
  }
}
