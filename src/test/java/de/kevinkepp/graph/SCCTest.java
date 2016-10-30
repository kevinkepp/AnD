package de.kevinkepp.graph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

@RunWith(value = Parameterized.class)
public class SCCTest extends GraphUnweightedTest {

  public SCCTest(Class<? extends GraphUnweighted> c) {
    super(c);
  }

  @Test
  public void test() {
    prepareGraphScc1();
    System.out.println(g);
    System.out.println("SCC.run");
    Map<Integer, Integer> leaders = new SCC<Integer>().run(g);
    for (Map.Entry<Integer, Integer> e : leaders.entrySet())
      System.out.println("leader(" + e.getKey() + ") = " + e.getValue());
    // TODO actual testing
  }
}

