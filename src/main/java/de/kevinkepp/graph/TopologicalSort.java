package de.kevinkepp.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TopologicalSort<V> {

  private Set<V> explored;
  private Map<V, Integer> labels;
  private int currentLabel = -1;

  public Map<V, Integer> run(Graph<V> g) {
    explored = new HashSet<>();
    labels = new HashMap<>();
    currentLabel = g.getNumberOfVertices();
    for (V v : g.getVertices())
      if (!explored.contains(v))
        dfs(g, v);
    return labels;
  }

  private void dfs(Graph<V> g, V s) {
    explored.add(s);
    for (V w : g.getAdjacentVertices(s))
      if (!explored.contains(w))
        dfs(g, w);
    labels.put(s, currentLabel);
    currentLabel--;
  }
}
