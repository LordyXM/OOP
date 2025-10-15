package org.example;

import java.util.*;

public class MyHashMultiMap<K, V> implements MultiMap<K, V> {
    private final Map<K, Collection<V>> map;

    public MyHashMultiMap() {
        this.map = new MyHashMap<>();
    }

    @Override
    public void put(K key, V value) {
        Collection<V> values = map.get(key);
        if (values == null) {
            values = new ArrayList<>();
            map.put(key, values);
        }
        values.add(value);
    }

    @Override
    public Collection<V> get(K key) {
        Collection<V> values = map.get(key);
        return values != null ? new ArrayList<>(values) : Collections.emptyList();
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        for (Collection<V> values : map.values()) {
            if (values.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public void remove(K key, V value) {
        Collection<V> values = map.get(key);
        if (values != null) {
            values.remove(value);
            if (values.isEmpty()) {
                map.remove(key);
            }
        }
    }

    @Override
    public int size() {
        int totalSize = 0;
        for (Collection<V> values : map.values()) {
            totalSize += values.size();
        }
        return totalSize;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> allValues = new ArrayList<>();
        for (Collection<V> values : map.values()) {
            allValues.addAll(values);
        }
        return allValues;
    }

    public int getValueCount(K key) {
        Collection<V> values = map.get(key);
        return values != null ? values.size() : 0;
    }

    public void printInternalStructure() {
        System.out.println("=== Internal Structure of HashMultiMap ===");
        System.out.println("Total values: " + size());
        System.out.println("Unique keys: " + map.size());

        for (K key : map.keySet()) {
            Collection<V> values = map.get(key);
            System.out.println("Key: " + key + " -> Values: " + values);
        }
        System.out.println();
    }
}