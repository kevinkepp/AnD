package de.kevinkepp.heap;

import org.junit.Test;

public class HeapImplTest {

  @Test
  public void test() {
    Heap<Integer> heap = new HeapImpl(false);
    heap.push(9);
    System.out.println(heap.toString());
    heap.push(7);
    System.out.println(heap.toString());
    heap.push(1);
    System.out.println(heap.toString());
    System.out.println(heap.pop());
    System.out.println(heap.toString());
    System.out.println(heap.pop());
    // TODO actual testing
  }
}

