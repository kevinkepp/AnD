package de.kevinkepp.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

abstract class GraphTest {

  protected Integer[] getVertices(int n) {
    return getIntegerVerticesFromTo(1, n);
  }

  // returns vertices {from, from+1, ..., to-1, to}
  protected Integer[] getIntegerVerticesFromTo(int from, int to) {
    List<Integer> vs = new ArrayList<>();
    for (int i = from; i <= to; i++)
      vs.add(i);
    return vs.toArray(new Integer[]{});
  }

  protected Integer[] getIntegerVerticesFromFile(String file) {
    List<Integer> vs = new ArrayList<>();
    try (Stream<String> stream = Files.lines(Paths.get(file))) {
      stream.forEach(line -> {
        String[] vals = line.split(" ");
        int v1 = Integer.valueOf(vals[0]);
        int v2 = Integer.valueOf(vals[1]);
        if (!vs.contains(v1))
          vs.add(v1);
        if (!vs.contains(v2))
          vs.add(v2);
      });
      return vs.toArray(new Integer[]{});
    } catch (IOException e) {
      System.err.println(e);
      return new Integer[]{};
    }
  }

  protected void addVertices(Graph<Integer> g, Integer[] vs) {
    for (int i : vs)
      g.addVertex(i);
  }
}

