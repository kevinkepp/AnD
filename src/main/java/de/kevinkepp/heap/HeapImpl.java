package de.kevinkepp.heap;

import java.util.ArrayList;
import java.util.Collections;

public class HeapImpl implements Heap<Integer> {

  private final ArrayList<Integer> vals;
  private final boolean min;

  public HeapImpl() {
    this(true);
  }

  public HeapImpl(boolean min) {
    vals = new ArrayList<>();
    this.min = min;
  }

  private int getParentIndex(int i) {
    return i == 0 ? 0 : (int) ((i + 1) / 2) - 1;
  }

  private int getFirstChildIndex(int i) {
    return (i + 1) * 2 - 1;
  }

  private int getSecondChildIndex(int i) {
    return (i + 1) * 2;
  }

  private int getSmallerChildIndex(int i) {
    int c1i = getFirstChildIndex(i);
    int c2i = getSecondChildIndex(i);
    int c1 = vals.get(c1i);
    if (!isValidIndex(c2i))
      return c1i;
    int c2 = vals.get(c2i);
    return c1 < c2 ? c1i : c2i;
  }

  private boolean isValidIndex(int i) {
    return i < vals.size();
  }

  private boolean hasFirstChild(int i) {
    return isValidIndex(getFirstChildIndex(i));
  }

  private boolean hasSecondChild(int i) {
    return isValidIndex(getSecondChildIndex(i));
  }

  public Integer peek() {
    return min ? vals.get(0) : -vals.get(0);
  }

  public void push(Integer o) {
    if (!min)
      o *= -1;
    vals.add(o);
    int i = vals.size() - 1;
    while (o < vals.get(getParentIndex(i))) {
      Collections.swap(vals, i, getParentIndex(i));
      i = getParentIndex(i);
    }
  }

  public Integer pop() {
    Integer oldRoot = vals.get(0);
    Integer root = vals.get(vals.size() - 1);
    vals.set(0, root);
    vals.remove(vals.size() - 1);
    int i = 0;
    while (hasFirstChild(i) && (root > vals.get(getFirstChildIndex(i))
      || hasSecondChild(i) && root > vals.get(getSecondChildIndex(i)))) {
      int ci = getSmallerChildIndex(i);
      Collections.swap(vals, i, ci);
      i = ci;
    }
    return min ? oldRoot : -oldRoot;
  }

  public int size() {
    return vals.size();
  }

  public String toString() {
    String s = "";
    for (int i : vals)
      s += (min ? i : -i) + " ";
    return s;
  }
}
