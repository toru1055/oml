package jp.thotta.oml;

import java.io.File;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ValidatorTest extends TestCase {
  public void testValidator() {
    InstanceMaker train = new SVMFileInstanceMaker(new File("test/a8a.train"));
    double[] lambdas = {0.001, 0.01, 0.1, 1, 10, 100, 1000};
    Learner[] learners = new Learner[lambdas.length];
    for(int i = 0; i < lambdas.length; i++) {
      learners[i] = new SGD(lambdas[i], 0.1);
      //learners[i] = new Perceptron(lambdas[i], 0.1);
      //learners[i] = new PA1(1.0, lambdas[i]);
      //learners[i] = new PA2(1.0, lambdas[i]);
    }
    Validator valid = new Validator(train, learners);
    Learner ml = valid.crossValidation(10);

    Evaluator eval = new Evaluator();
    InstanceMaker test = new SVMFileInstanceMaker(new File("test/a8a.test"));
    while(test.hasNext()) {
      Instance i = test.nextInstance();
      int pred = ml.predict(i.x);
      eval.add(i.label, pred);
    }
    eval.printAll("\nML logic is " + ml.getClass().getName());
  }
}
