package de.kevinkepp.graph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value = Parameterized.class)
public class DFSTest extends GraphUnweightedTest {

  public DFSTest(Class<? extends GraphUnweighted> c) {
    super(c);
  }

  @Test
  public void test() {
    prepareGraphCyclic1();
    System.out.println("DFS.run");
    new DFS<Integer>().run(g, g.getVertices().iterator().next());
    // TODO actual testing
    System.out.println("DFS.runRecursive");
    new DFS<Integer>().runRecursive(g, g.getVertices().iterator().next());
    // TODO actual testing
  }

}
