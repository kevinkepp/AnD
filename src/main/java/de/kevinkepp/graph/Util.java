package de.kevinkepp.graph;

import java.util.*;

public class Util {

  public static boolean ASC = true;
  public static boolean DESC = false;


  public static <T> Map<T, Integer> sortMapByValue(Map<T, Integer> unsortMap, final boolean order) {
    List<Map.Entry<T, Integer>> list = new LinkedList<Map.Entry<T, Integer>>(unsortMap.entrySet());
    // Sorting the list based on values
    Collections.sort(list, new Comparator<Map.Entry<T, Integer>>() {
      public int compare(Map.Entry<T, Integer> o1, Map.Entry<T, Integer> o2) {
        if (order)
          return o1.getValue().compareTo(o2.getValue());
        else
          return o2.getValue().compareTo(o1.getValue());
      }
    });
    // Maintaining insertion order with the help of LinkedList
    Map<T, Integer> sortedMap = new LinkedHashMap<T, Integer>();
    for (Map.Entry<T, Integer> entry : list)
      sortedMap.put(entry.getKey(), entry.getValue());
    return sortedMap;
  }
}
