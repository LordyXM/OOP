package org.example;

import java.util.Collection;
import java.util.Set;

public interface MultiMap<K, V> {
    void put(K key, V value);
    Collection<V> get(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    void remove(K key);
    void remove(K key, V value);
    int size();
    boolean isEmpty();
    void clear();
    Set<K> keySet();
    Collection<V> values();
}