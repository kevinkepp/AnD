package de.kevinkepp.graph;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public abstract class GraphAdjMatrix<V> implements Graph<V> {

  protected final int NO_EDGE = 0;
  protected List<V> vertices = new ArrayList<>();

  // outgoing edges matrix
  protected List<List<Integer>> matrix = new ArrayList<>();

  protected void ensureEdge(int i) {
    if (matrix.size() < i + 1)
      matrix.add(new ArrayList<>());
  }

  protected void addEdge(int i, int j, int w) {
    ensureEdge(i);
    List<Integer> list = matrix.get(i);
    list.add(j, w);
  }

  protected void addEdgeWeighted(V from, V to, int w) {
    if (!vertices.contains(from) || !vertices.contains(to))
      throw new IllegalStateException("Can't add edge to non-existing vertices");
    int fromIdx = vertices.indexOf(from);
    int toIdx = vertices.indexOf(to);
    addEdge(fromIdx, toIdx, w);
  }

  protected Map<V, Integer> getOutgoingEdgesWeighted(V v) {
    if (!vertices.contains(v))
      throw new IllegalStateException("Can't find edge for non-existing vertices");
    Map<V, Integer> res = new HashMap<>();
    List<Integer> row = matrix.get(vertices.indexOf(v));
    for (int c = 0; c < row.size(); c++) {
      res.put(vertices.get(c), row.get(c));
    }
    return res;
  }

  protected void copyTo(GraphAdjMatrix<V> to) {
    to.vertices = new ArrayList<>(vertices);
    to.matrix = new ArrayList<>();
    for (List<Integer> row : matrix)
      matrix.add(new ArrayList<>(row));
  }

  public void addVertex(V v) {
    vertices.add(v);
    // extend matrix
    // add column
    for (List<Integer> row : matrix)
      row.add(NO_EDGE);
    // add row
    List<Integer> row = new ArrayList<>(getNumberOfVertices());
    for (int i = 0; i < getNumberOfVertices(); i++)
      row.add(NO_EDGE);
    matrix.add(row);
  }

  public boolean containsVertex(V v) {
    return vertices.contains(v);
  }

  public int getNumberOfVertices() {
    return vertices.size();
  }

  public int getNumberOfEdges() {
    int n = 0;
    for (List<Integer> e : matrix)
      for (int i : e)
        if (i != NO_EDGE)
          n += 1;
    return n;
  }

  public Set<V> getVertices() {
    return new HashSet<>(vertices);
  }

  public Set<V> getAdjacentVertices(V v) {
    return getOutgoingEdgesWeighted(v).keySet();
  }

  public void reverse() {
    throw new NotImplementedException();
  }
}







