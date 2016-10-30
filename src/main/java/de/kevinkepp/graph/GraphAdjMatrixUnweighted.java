package de.kevinkepp.graph;

public class GraphAdjMatrixUnweighted<V> extends GraphAdjMatrix<V> implements GraphUnweighted<V> {

  private final int EDGE = 1;

  public void addEdge(V from, V to) {
    addEdgeWeighted(from, to, EDGE);
  }

  public GraphUnweighted<V> copy() {
    GraphAdjMatrixUnweighted<V> g = new GraphAdjMatrixUnweighted<>();
    super.copyTo(g);
    return g;
  }
}







