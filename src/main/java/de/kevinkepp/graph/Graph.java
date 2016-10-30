package de.kevinkepp.graph;

import java.util.Set;

public interface Graph<V> {

  // n
  int getNumberOfVertices();

  // m
  int getNumberOfEdges();

  void addVertex(V v);

  boolean containsVertex(V v);

  Set<V> getVertices();

  Set<V> getAdjacentVertices(V v);

  Graph<V> copy();

  void reverse();

  default boolean equals(GraphUnweighted<V> g) {
    if (!getVertices().equals(g.getVertices()))
      return false;
    for (V v : getVertices())
      if (!getAdjacentVertices(v).equals(g.getAdjacentVertices(v)))
        return false;
    return true;
  }
}
