package de.kevinkepp.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Special implementation for integer vertices using a fixed-sized int-array of sets instead of lists of sets like
 * GraphAdjListUnweighted.
 */
public class GraphAdjListUnweightedInt implements GraphUnweighted<Integer> {
  // outgoing matrix
  private Set<Integer>[] edges;
  private Set<Integer> vertices;

  public GraphAdjListUnweightedInt(int n) {
    edges = (Set<Integer>[]) new HashSet[n];
    vertices = new HashSet<>(n);
  }

  private static void ensureEdge(Set<Integer>[] edges, int i) {
    if (edges[i] == null)
      edges[i] = new HashSet<>();
  }

  private static void addEdge(Set<Integer>[] edges, int i, int j) {
    ensureEdge(edges, i);
    edges[i].add(j);
  }

  private void ensureEdge(int i) {
    ensureEdge(edges, i);
  }

  private void addEdge(int i, int j) {
    addEdge(edges, i, j);
  }

  public void addVertex(Integer i) {
    vertices.add(i);
  }

  public void addEdge(Integer from, Integer to) {
    //System.out.println("Add edge: " + from + "-" + to);
    addEdge(from, to);
  }

  public int getNumberOfVertices() {
    return vertices.size();
  }

  public int getNumberOfEdges() {
    int n = 0;
    for (Set<Integer> es : edges)
      n += es.size();
    return n;
  }

  public Set<Integer> getAdjacentVertices(Integer i) {
    ensureEdge(i);
    return edges[i];
  }

  public boolean containsVertex(Integer i) {
    return vertices.contains(i);
  }

  public Set<Integer> getVertices() {
    return new HashSet<>(vertices);
  }

  public GraphUnweighted<Integer> copy() {
    GraphAdjListUnweightedInt g = new GraphAdjListUnweightedInt(edges.length);
    g.vertices = new HashSet<>(vertices);
    g.edges = (Set<Integer>[]) new HashSet[edges.length];
    for (int i = 0; i < edges.length; i++)
      g.edges[i] = edges[i] != null ? new HashSet<>(edges[i]) : null;
    return g;
  }

  public void reverse() {
    Set<Integer>[] newEdges = (Set<Integer>[]) new HashSet[edges.length];
    for (int i = 0; i < edges.length; i++)
      if (edges[i] != null)
        for (int e : edges[i])
          // insert reverse edge
          addEdge(newEdges, e, i);
    edges = newEdges;
  }

  private void printEdges() {
    for (int i = 0; i < edges.length; i++) {
      System.out.print(i + ": ");
      if (edges[i] != null)
        for (int v : edges[i])
          System.out.print(v + " ");
      System.out.println();
    }
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < edges.length; i++) {
      Set<Integer> m = edges[i];
      if (m != null) {
        s.append(i + ": ");
        if (m != null)
          for (int v : m)
            s.append(v + " ");
        s.append("\n");
      }
    }
    return s.toString();
  }
}







