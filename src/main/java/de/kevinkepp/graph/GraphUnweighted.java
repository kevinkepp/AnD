package de.kevinkepp.graph;

public interface GraphUnweighted<V> extends Graph<V> {

  void addVertex(V v);

  void addEdge(V from, V to);

  boolean containsVertex(V v);

  GraphUnweighted<V> copy();
}
