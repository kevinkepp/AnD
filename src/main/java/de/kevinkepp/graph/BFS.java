package de.kevinkepp.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS<V> {

  private Set<V> explored = new HashSet<>();

  public void run(Graph<V> g, V s) {
    Queue<V> queue = new LinkedList<>();
    queue.offer(s);
    while (!queue.isEmpty()) {
      V v = queue.poll();
      if (!explored.contains(v)) {
        explored.add(v);
        System.out.println("Visit " + v);
        for (V w : g.getAdjacentVertices(v))
          if (!explored.contains(w))
            queue.offer(w);
      }
    }
  }
}
