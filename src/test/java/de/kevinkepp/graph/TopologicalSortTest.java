package de.kevinkepp.graph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

@RunWith(value = Parameterized.class)
public class TopologicalSortTest extends GraphUnweightedTest {

  public TopologicalSortTest(Class<? extends GraphUnweighted> c) {
    super(c);
  }

  @Test
  public void findRightTopologicalOrdering() {
    System.out.println("TopologicalSort.run");
    Map<Integer, Integer> labels = new TopologicalSort<Integer>().run(g);
    for (Map.Entry<Integer, Integer> e : labels.entrySet())
      System.out.println(e.getKey() + " - " + e.getValue());
  }
}

