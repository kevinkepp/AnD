package de.kevinkepp.graph;

import java.util.Map;

public interface GraphWeighted<V> extends Graph<V> {

  void addVertex(V v);

  void addEdge(V from, V to, int weight);

  boolean containsVertex(V v);

  Map<V, Integer> getOutgoingEdges(V v);

  GraphWeighted<V> copy();

  default boolean equals(GraphWeighted<V> g) {
    if (!getVertices().equals(g.getVertices()))
      return false;
    for (V v : getVertices())
      if (!getOutgoingEdges(v).equals(g.getOutgoingEdges(v)))
        return false;
    return true;
  }
}
