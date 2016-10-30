package de.kevinkepp.graph;

import java.util.Map;

public class GraphAdjMatrixWeighted<V> extends GraphAdjMatrix<V> implements GraphWeighted<V> {

  public void addEdge(V from, V to, int weight) {
    super.addEdgeWeighted(from, to, weight);
  }

  public Map<V, Integer> getOutgoingEdges(V v) {
    return super.getOutgoingEdgesWeighted(v);
  }

  public GraphWeighted<V> copy() {
    GraphAdjMatrixWeighted<V> g = new GraphAdjMatrixWeighted<>();
    super.copyTo(g);
    return g;
  }
}







