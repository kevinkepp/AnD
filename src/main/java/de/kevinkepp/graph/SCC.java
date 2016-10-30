package de.kevinkepp.graph;

import java.util.*;

// Kosaraju's Two-Pass
public class SCC<V> {

  private Map<V, V> leaders;
  private Map<V, Integer> finishingTimes;
  private Set<V> explored;
  private int t;
  private V s;

  public Map<V, V> run(Graph<V> g) {
    System.out.println("create reversed copy of graph");
    Graph<V> reversed = g.copy();
    reversed.reverse();
    System.out.println("run first pass");
    finishingTimes = new HashMap<>();
    scc(reversed);
    System.out.println("run second pass");
    scc(g);
    return leaders;
  }

  private void scc(Graph<V> g) {
    leaders = new HashMap<>();
    explored = new HashSet<>();
    t = 0;
    s = null;
    Collection<V> vs = finishingTimes.size() == 0 ? g.getVertices() : Util.sortMapByValue(finishingTimes, Util.DESC).keySet();
    for (V v : vs) {
      if (!explored.contains(v)) {
        s = v;
        dfsIter(g, v);
      }
    }
  }

  private void dfs(Graph<V> g, V i) {
    explored.add(i);
    leaders.put(i, s);
    for (V j : g.getAdjacentVertices(i))
      if (!explored.contains(j))
        dfs(g, j);
    t++;
    finishingTimes.put(i, t);
  }

  private void dfsIter(Graph<V> g, V i) {
    Stack<V> stack = new Stack<>();
    stack.push(i);
    while (!stack.isEmpty()) {
      V v = stack.pop();
      if (!explored.contains(v)) {
        explored.add(v);
        stack.push(v); // add it back to the stack
        leaders.put(v, s);
        for (V w : g.getAdjacentVertices(v))
          if (!explored.contains(w))
            stack.push(w);
      } else if (!finishingTimes.containsKey(v)) {
        t++;
        finishingTimes.put(v, t);
      }
    }
  }
}
