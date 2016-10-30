package de.kevinkepp.graph;

import org.junit.Before;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class GraphUnweightedTest extends GraphTest {

  private Class graphClass;
  protected GraphUnweighted<Integer> g;

  public GraphUnweightedTest(Class<? extends GraphUnweighted> c) {
    graphClass = c;
  }

  @Before
  public void setUp() throws IllegalAccessException, InstantiationException {
    g = (GraphUnweighted) graphClass.newInstance();
  }

  @Parameterized.Parameters
  public static Collection getGraphUnweightedImpls() {
    return Arrays.asList(GraphAdjListUnweighted.class, GraphAdjListUnweightedInt.class,
      GraphAdjMatrixUnweighted.class);
  }

  protected void prepareGraphAcyclic1() {
    Integer[] vs = getVertices(6);
    addVertices(g, vs);
    g.addEdge(vs[0], vs[1]);
    g.addEdge(vs[0], vs[2]);
    g.addEdge(vs[1], vs[3]);
    g.addEdge(vs[2], vs[4]);
    g.addEdge(vs[3], vs[4]);
    g.addEdge(vs[4], vs[5]);
  }

  protected void prepareGraphCyclic1() {
    Integer[] vs = getVertices(6);
    addVertices(g, vs);
    g.addEdge(vs[0], vs[1]);
    g.addEdge(vs[0], vs[2]);
    g.addEdge(vs[1], vs[3]);
    g.addEdge(vs[2], vs[4]);
    g.addEdge(vs[3], vs[4]);
    g.addEdge(vs[4], vs[5]);
    // cycles
    g.addEdge(vs[1], vs[2]);
    g.addEdge(vs[3], vs[5]);
  }

  protected void prepareGraphScc1() {
    Integer[] vs = getVertices(9);
    addVertices(g, vs);
    // comp1
    g.addEdge(vs[0], vs[6]);
    g.addEdge(vs[6], vs[3]);
    g.addEdge(vs[3], vs[0]);
    // con
    g.addEdge(vs[6], vs[8]);
    // comp 2
    g.addEdge(vs[8], vs[5]);
    g.addEdge(vs[5], vs[2]);
    g.addEdge(vs[2], vs[8]);
    // con
    g.addEdge(vs[5], vs[7]);
    // comp 3
    g.addEdge(vs[7], vs[1]);
    g.addEdge(vs[1], vs[4]);
    g.addEdge(vs[4], vs[7]);
  }
}

