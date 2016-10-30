package de.kevinkepp.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphAdjListUnweighted<V> implements GraphUnweighted<V> {

  List<V> vertices = new ArrayList<>();

  // outgoing matrix
  List<Set<Integer>> edges = new ArrayList<>();

  private static void ensureEdge(List<Set<Integer>> edges, int i) {
    while (edges.size() < i + 1)
      edges.add(new HashSet<>());
  }

  private static void addEdge(List<Set<Integer>> edges, int i, int j) {
    ensureEdge(edges, i);
    edges.get(i).add(j);
  }

  private void ensureEdge(int i) {
    ensureEdge(edges, i);
  }

  private void addEdge(int i, int j) {
    addEdge(edges, i, j);
  }

  public void addVertex(V v) {
    if (!vertices.contains(v)) {
      //System.out.println("Add vertex " + v);
      vertices.add(v);
    }
  }

  public void addEdge(V from, V to) {
    if (!vertices.contains(from) || !vertices.contains(to))
      throw new IllegalStateException("Can't find edge for non-existing vertices");
    //System.out.println("Add edge " + from + "-" + to);
    int fromIdx = vertices.indexOf(from);
    int toIdx = vertices.indexOf(to);
    addEdge(fromIdx, toIdx);
  }

  public int getNumberOfVertices() {
    return vertices.size();
  }

  public int getNumberOfEdges() {
    int n = 0;
    for (Set<Integer> e : edges)
      n += e.size();
    return n;
  }

  public Set<V> getAdjacentVertices(V v) {
    int idx = vertices.indexOf(v);
    ensureEdge(idx);
    Set<V> vs = new HashSet<>();
    for (int i : edges.get(idx)) {
      vs.add(vertices.get(i));
    }
    return vs;
  }

  public boolean containsVertex(V v) {
    return vertices.contains(v);
  }

  public Set<V> getVertices() {
    return new HashSet<>(vertices);
  }

  public GraphUnweighted<V> copy() {
    GraphAdjListUnweighted<V> g = new GraphAdjListUnweighted<>();
    g.vertices = new ArrayList<>(vertices);
    g.edges = new ArrayList<>(edges.size());
    for (Set<Integer> e : edges)
      g.edges.add(new HashSet<>(e));
    return g;
  }

  public void reverse() {
    List<Set<Integer>> newEdges = new ArrayList<>();
    for (int i = 0; i < edges.size(); i++)
      for (int e : edges.get(i))
        // insert reverse edge
        addEdge(newEdges, e, i);
    edges = newEdges;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < edges.size(); i++) {
      Set<Integer> m = edges.get(i);
      s.append(vertices.get(i) + ": ");
      for (int v : m)
        s.append(vertices.get(v) + " ");
      s.append("\n");
    }
    return s.toString();
  }
}







