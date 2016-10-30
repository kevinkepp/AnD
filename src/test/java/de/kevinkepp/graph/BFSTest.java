package de.kevinkepp.graph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value = Parameterized.class)
public class BFSTest extends GraphUnweightedTest {

  public BFSTest(Class<? extends GraphUnweighted> c) {
    super(c);
  }

  @Test
  public void test() {
    prepareGraphCyclic1();
    System.out.println("BFS.run");
    new BFS<Integer>().run(g, g.getVertices().iterator().next());
    // TODO actual testing
  }

}
