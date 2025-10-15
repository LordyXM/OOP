package org.example;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    void remove(K key);
    int size();
    boolean isEmpty();
    void clear();
    Set<K> keySet();
    Collection<V> values();
}