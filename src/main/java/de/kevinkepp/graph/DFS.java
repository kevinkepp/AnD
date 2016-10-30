package de.kevinkepp.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS<V> {

  private Set<V> explored = new HashSet<>();

  public void run(Graph<V> g, V s) {
    Stack<V> stack = new Stack<>();
    stack.push(s);
    while (!stack.isEmpty()) {
      V v = stack.pop();
      if (!explored.contains(v)) {
        explored.add(v);
        System.out.println("Visit " + v);
        for (V w : g.getAdjacentVertices(v))
          if (!explored.contains(w))
            stack.push(w);
      }
    }
  }

  public void runRecursive(Graph<V> g, V s) {
    System.out.println("Visit " + s);
    explored.add(s);
    for (V w : g.getAdjacentVertices(s))
      if (!explored.contains(w))
        runRecursive(g, w);
  }
}
