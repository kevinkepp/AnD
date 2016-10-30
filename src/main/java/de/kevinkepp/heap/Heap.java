package de.kevinkepp.heap;

public interface Heap<T> {

  T peek();

  void push(T o);

  T pop();

  int size();
}
