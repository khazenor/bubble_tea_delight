package khazenor.bubble_tea_delight.data_structs;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapList<K, V> extends HashMap<K, ArrayList<V>> {
  public void addToList(K key, V value) {
    if (this.containsKey(key)) {
      this.get(key).add(value);
    } else {
      this.put(key, new ArrayList<>());
    }
  }
}
