package de.kevinkepp.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra<V> {

  private static final int INF = Integer.MAX_VALUE;

  private class QueueVertex<V> implements Comparable<QueueVertex<V>> {

    V v;
    int val;

    QueueVertex(V v, int val) {
      this.v = v;
      this.val = val;
    }

    public int compareTo(QueueVertex<V> w) {
      return Integer.compare(val, w.val);
    }
  }

  public Map<V, Integer> run(GraphWeighted<V> g, V s) {
    Map<V, Integer> dist = new HashMap<>();
    dist.put(s, 0);

    PriorityQueue<QueueVertex<V>> q = new PriorityQueue<>();
    for (V v : g.getVertices()) {
      if (!s.equals(v))
        dist.put(v, INF);
      q.add(new QueueVertex<V>(v, dist.get(v)));
    }

    while (q.size() > 0) {
      V u = q.poll().v;
      for (Map.Entry<V, Integer> vs : g.getOutgoingEdges(u).entrySet()) {
        V v = vs.getKey();
        int lengthUV = vs.getValue();
        int alt = dist.get(u) + lengthUV;
        if (alt < dist.get(v)) {
          dist.put(v, alt);
          q.add(new QueueVertex(v, alt));
        }
      }
    }
    return dist;
  }
}
