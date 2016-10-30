package de.kevinkepp.graph;

import java.util.*;

public class GraphAdjListWeighted<V> implements GraphWeighted<V> {

  List<V> vertices = new ArrayList<>();

  // outgoing matrix
  List<Map<Integer, Integer>> edges = new ArrayList<>();

  private static void ensureEdge(List<Map<Integer, Integer>> edges, int i) {
    while (edges.size() < i + 1)
      edges.add(new HashMap<>());
  }

  private static void addEdge(List<Map<Integer, Integer>> edges, int i, int j, int w) {
    ensureEdge(edges, i);
    Map<Integer, Integer> map = edges.get(i);
    map.put(j, w);
  }

  private void ensureEdge(int i) {
    ensureEdge(edges, i);
  }

  private void addEdge(int i, int j, int w) {
    addEdge(edges, i, j, w);
  }

  public void addVertex(V v) {
    if (!vertices.contains(v)) {
      //System.out.println("Add vertex " + v);
      vertices.add(v);
    }
  }

  public void addEdge(V from, V to, int weight) {
    if (!vertices.contains(from) || !vertices.contains(to))
      throw new IllegalStateException("Can't find edge for non-existing vertices");
    //System.out.println("Add edge " + from + "-" + to);
    int fromIdx = vertices.indexOf(from);
    int toIdx = vertices.indexOf(to);
    addEdge(fromIdx, toIdx, weight);
  }

  public int getNumberOfVertices() {
    return vertices.size();
  }

  public int getNumberOfEdges() {
    int n = 0;
    for (Map<Integer, Integer> e : edges)
      n += e.size();
    return n;
  }

  public Set<V> getAdjacentVertices(V v) {
    return getOutgoingEdges(v).keySet();
  }

  public Map<V, Integer> getOutgoingEdges(V v) {
    int idx = vertices.indexOf(v);
    ensureEdge(idx);
    Map<Integer, Integer> es = edges.get(idx);
    Map<V, Integer> vs = new HashMap<>();
    for (Map.Entry<Integer, Integer> e : es.entrySet()) {
      vs.put(vertices.get(e.getKey()), e.getValue());
    }
    return vs;
  }

  public boolean containsVertex(V v) {
    return vertices.contains(v);
  }

  public Set<V> getVertices() {
    return new HashSet(vertices);
  }

  public GraphWeighted<V> copy() {
    GraphAdjListWeighted<V> g = new GraphAdjListWeighted<>();
    g.vertices = new ArrayList<>(vertices);
    g.edges = new ArrayList<>(edges.size());
    for (Map<Integer, Integer> e : edges)
      g.edges.add(new HashMap<>(e));
    return g;
  }

  public void reverse() {
    List<Map<Integer, Integer>> newEdges = new ArrayList<>();
    for (int i = 0; i < edges.size(); i++)
      for (Map.Entry<Integer, Integer> e : edges.get(i).entrySet())
        // insert reverse edge
        addEdge(newEdges, e.getKey(), i, e.getValue());
    edges = newEdges;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < edges.size(); i++) {
      Map<Integer, Integer> m = edges.get(i);
      s.append(vertices.get(i) + ": ");
      for (Map.Entry<Integer, Integer> e : m.entrySet())
        s.append(vertices.get(e.getKey()) + "," + e.getValue() + " ");
      s.append("\n");
    }
    return s.toString();
  }
}







