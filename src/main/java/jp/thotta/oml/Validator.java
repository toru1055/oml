package jp.thotta.oml;

public class Validator {
  InstanceMaker instanceMaker;
  Learner[] learners;
  Evaluator[][] evals;

  public Validator(InstanceMaker im, Learner[] l) {
    this.instanceMaker = im;
    this.learners = l;
  }

  public Learner crossValidation(int fold) {
    evals = new Evaluator[learners.length][];
    for(int i = 0; i < evals.length; i++) {
      evals[i] = new Evaluator[fold];
      for(int j = 0; j < fold; j++) {
        evals[i][j] = new Evaluator();
      }
    }
    for(int j = 0; j < fold; j++) {
      initLearners();
      for(int k = 0; k < 5; k++) {
        train(fold, j);
      }
      test(fold, j);
    }
    int maxKey = 0;
    double maxAccuracy = 0.0;
    for(int i = 0; i < evals.length; i++) {
      double accuracy = averageAccuracy(evals[i]);
      System.out.println(i + ": accuracy: " + accuracy);
      if(accuracy > maxAccuracy) {
        maxAccuracy = accuracy;
        maxKey = i;
      }
    }
    initLearners();
    for(int k = 0; k < 5; k++) {
      trainAll(learners[maxKey]);
    }
    return learners[maxKey];
  }

  void initLearners() {
    for(int i = 0; i < learners.length; i++) {
      learners[i].init();
    }
  }

  void trainAll(Learner l) {
    instanceMaker.init();
    while(instanceMaker.hasNext()) {
      Instance instance = instanceMaker.nextInstance();
      l.update(instance.x, instance.label);
    }
  }

  void train(int foldNum, int testFold) {
    int line = 0;
    instanceMaker.init();
    while(instanceMaker.hasNext()) {
      Instance instance = instanceMaker.nextInstance();
      if(line++ % foldNum == testFold) {
        // Test fold
      } else {
        // Train fold 
        for(int i = 0; i < learners.length; i++) {
          learners[i].update(instance.x, instance.label);
        }
      }
    }
  }

  void test(int foldNum, int testFold) {
    int line = 0;
    instanceMaker.init();
    while(instanceMaker.hasNext()) {
      Instance instance = instanceMaker.nextInstance();
      if(line++ % foldNum == testFold) {
        // Test fold
        for(int i = 0; i < evals.length; i++) {
          int pred = learners[i].predict(instance.x);
          evals[i][testFold].add(instance.label, pred);
        }
      } else {
        // Train fold 
      }
    }
  }

  static double averageAccuracy(Evaluator[] eval) {
    double ave = 0.0;
    for(Evaluator e : eval) {
      //ave += e.getFmeasure();
      ave += e.getAccuracy();
      //ave += e.getPrecision();
      //ave += e.getRecall();
    }
    return ave / eval.length;
  }
}
