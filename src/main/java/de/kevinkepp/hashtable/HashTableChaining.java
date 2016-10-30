package de.kevinkepp.hashtable;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTableChaining<K, V> implements HashTable<K, V> {

  private Bucket[] buckets;
  private int maxDepth = 0; // for printing
  private int maxValueWidth = 0; // for printing

  private static final int MIN_BUCKETS = (int) Math.pow(2, 3);

  public HashTableChaining(int objectCount) {
    int n = getNumberOfBuckets(objectCount);
    buckets = (Bucket[]) Array.newInstance(Bucket.class, n);
  }

  private int hash(K key) {
    int hc = key.hashCode();
    return Math.abs(hc) % buckets.length;
  }

  public V get(K key) {
    int h = hash(key);
    if (buckets[h] != null)
      for (Entry e : buckets[h])
        if (e.getKey().equals(key))
          return e.getValue();
    return null;
  }

  public void put(K key, V value) {
    int h = hash(key);
    if (buckets[h] == null)
      buckets[h] = new Bucket();
    // no duplicates allowed, TODO is there a more efficient solution?
    Iterator<Entry> iter = buckets[h].iterator();
    while (iter.hasNext())
      if (iter.next().getKey().equals(key))
        iter.remove();
    buckets[h].addFirst(new Entry(key, value));
    if (buckets[h].size() > maxDepth)
      maxDepth = buckets[h].size();
    if (value.toString().length() > maxValueWidth)
      maxValueWidth = value.toString().length();
  }

  public boolean contains(V value) {
    for (Bucket b : buckets)
      if (b != null)
        for (Entry e : b)
          if (e.getValue().equals(value))
            return true;
    return false;
  }

  public V remove(K key) {
    int h = hash(key);
    Iterator<Entry> iter = buckets[h].iterator();
    while (iter.hasNext()) {
      Entry e = iter.next();
      if (e.getKey().equals(key)) {
        V value = e.getValue();
        iter.remove();
        return value;
      }
    }
    return null;
  }

  public boolean isEmpty() {
    for (Bucket b : buckets)
      if (b != null && !b.isEmpty())
        return false;
    return true;
  }

  public int getBucketCount() {
    return buckets.length;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int d = 0; d < maxDepth; ++d) {
      s.append("|");
      for (Bucket b : buckets) {
        if (b != null && b.size() > d) {
          V val = b.get(d).getValue();
          s.append(padString(val.toString(), ' ', maxValueWidth));
        } else
          for (int i = 0; i < maxValueWidth; ++i)
            s.append(" ");
        s.append("|");
      }
      s.append("\n");
    }
    String r = s.toString();
    return r.substring(0, r.length() - 1);
  }

  private static int getNumberOfBuckets(int objectCount) {
    int candidate = objectCount > MIN_BUCKETS ? objectCount : MIN_BUCKETS;
    do {
      candidate = getNextPrime(candidate);
    } while (isCloseToPowerOf(2, candidate) || isCloseToPowerOf(10, candidate));
    return candidate;
  }

  private static int getNextPrime(int n) {
    return BigInteger.valueOf(n + 1).nextProbablePrime().intValue();
  }

  private static boolean isCloseToPowerOf(int base, int n) {
    double l = log(base, n);
    int lFloor = (int) l;
    return l < lFloor + 0.15 || l > lFloor + 0.85;
  }

  private static double log(int base, int n) {
    return Math.log(n) / Math.log(base);
  }

  class Bucket extends LinkedList<Entry> {
  }

  class Entry {

    private K key;
    private V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }

  private String padString(String s, char p, int n) {
    while (s.length() < n) {
      s += p;
    }
    return s;
  }
}
