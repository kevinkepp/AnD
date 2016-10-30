package de.kevinkepp.hashtable;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class HashTableChainingTest {

  private ArrayList<String> keys;
  private HashTableChaining<String, Integer> map;

  final int N = 1000;
  final int MAX = 10000;
  final int RUNS = 1000;
  final boolean VERBOSE = false;

  @Before
  public void setup() {
    // generate N distinct values
    ArrayList<Integer> values = new ArrayList<>();
    for (int k = 0; k < N; ++k) {
      int v;
      do {
        v = (int) (Math.random() * MAX);
      } while (values.contains(v));
      values.add(k, v);
    }
    if (values.size() != new LinkedHashSet<>(values).size())
      throw new IllegalStateException("Duplicate keys");
    // create a "small" hash map in order to force collisions
    int size = (int) (N * 0.25);
    map = new HashTableChaining<>(size);
    if (VERBOSE)
      System.out.println("HashTable with " + map.getBucketCount() + " buckets");
    // use strings of integer keys as values and store them in map
    keys = new ArrayList<String>();
    for (Integer v : values) {
      String k = v.toString();
      map.put(k, v);
      if (VERBOSE) {
        System.out.println("put ('" + k + "'," + v + ")");
        System.out.println(map.toString());
      }
      keys.add(k);
    }
  }

  private void runTest() {
    int i = (int) (Math.random() * keys.size());
    String k = keys.get(i);
    Integer v = map.get(k);
    if (v == null || !v.toString().equals(k))
      throw new IllegalStateException("get " + k);
    boolean c = map.contains(v);
    if (!c)
      throw new IllegalStateException("contains " + v);
    Integer vRemoved = map.remove(k);
    keys.remove(i);
    if (VERBOSE) {
      System.out.println("remove " + k);
      System.out.println(map.toString());
    }
    if (vRemoved == null || !vRemoved.equals(v))
      throw new IllegalStateException("remove " + k + ": " + vRemoved);
    Integer vGetRemoved = map.get(k);
    if (vGetRemoved != null)
      throw new IllegalStateException("get removed " + k + ": " + vGetRemoved);
  }

  @Test
  public void test() {
    for (int i = 0; i < (VERBOSE ? 1 : RUNS); ++i) {
      setup();
      if (!keys.isEmpty())
        runTest();
      else if (!map.isEmpty())
        throw new IllegalStateException("No more keys but map is not empty");
    }
  }
}
