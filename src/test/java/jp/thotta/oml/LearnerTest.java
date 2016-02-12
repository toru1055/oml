package jp.thotta.oml;

import java.io.File;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LearnerTest extends TestCase {
  public void testLearner() {
    Learner sgd = new SGD(0.1, 0.1);
    System.out.println("Accuracy(sgd) = " + a8a_learn(sgd));
    System.out.println("Accuracy(sgd) = " + a8a_learn(sgd));
    System.out.println("Accuracy(sgd) = " + a8a_learn(sgd));

    Learner perceptron = new Perceptron(0.1);
    System.out.println("Accuracy(perceptron) = " + a8a_learn(perceptron));
    System.out.println("Accuracy(perceptron) = " + a8a_learn(perceptron));
    System.out.println("Accuracy(perceptron) = " + a8a_learn(perceptron));

    Learner pa2 = new PA2(1000);
    System.out.println("Accuracy(pa2) = " + a8a_learn(pa2));
    System.out.println("Accuracy(pa2) = " + a8a_learn(pa2));
    System.out.println("Accuracy(pa2) = " + a8a_learn(pa2));

    Learner pa1 = new PA1(1000);
    System.out.println("Accuracy(pa1) = " + a8a_learn(pa1));
    System.out.println("Accuracy(pa2) = " + a8a_learn(pa1));
    System.out.println("Accuracy(pa2) = " + a8a_learn(pa1));

  }

  double a8a_learn(Learner ml) {
    Evaluator eval = new Evaluator();
    InstanceMaker train = new SVMFileInstanceMaker(new File("test/a8a.train"));
    while(train.hasNext()) {
      Instance i = train.nextInstance();
      ml.update(i.x, i.label);
    }
    InstanceMaker test = new SVMFileInstanceMaker(new File("test/a8a.test"));
    while(test.hasNext()) {
      Instance i = test.nextInstance();
      int pred = ml.predict(i.x);
      eval.add(i.label, pred);
    }
    eval.printAll("\nML logic is " + ml.getClass().getName());
    return eval.getAccuracy();
  }
}
