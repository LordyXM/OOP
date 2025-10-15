package org.example;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int size;
    private final float loadFactor;

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        final int hash;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) throw new IllegalArgumentException("Illegal initial capacity");
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) throw new IllegalArgumentException("Illegal load factor");

        this.loadFactor = loadFactor;
        this.table = (Node<K, V>[]) new Node[initialCapacity];
        this.size = 0;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode() & 0x7fffffff;
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        int index = hash % table.length;

        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash &&
                    (current.key == key || (key != null && key.equals(current.key)))) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        table[index] = new Node<>(hash, key, value, table[index]);
        size++;

        if ((float) size / table.length > loadFactor) {
            resize();
        }
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = hash % table.length;

        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash &&
                    (current.key == key || (key != null && key.equals(current.key)))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public void remove(K key) {
        int hash = hash(key);
        int index = hash % table.length;

        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.hash == hash &&
                    (current.key == key || (key != null && key.equals(current.key)))) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                keys.add(current.key);
                current = current.next;
            }
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                values.add(current.value);
                current = current.next;
            }
        }
        return values;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];

        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                Node<K, V> next = current.next;
                int newIndex = current.hash % newCapacity;
                current.next = newTable[newIndex];
                newTable[newIndex] = current;
                current = next;
            }
        }

        table = newTable;
    }

    public void printInternalStructure() {
        System.out.println("=== Internal Structure of HashMap ===");
        System.out.println("Size: " + size);
        System.out.println("Table length: " + table.length);

        for (int i = 0; i < table.length; i++) {
            System.out.print("Bucket " + i + ": ");
            Node<K, V> current = table[i];
            while (current != null) {
                System.out.print("[" + current.key + "=" + current.value + "] -> ");
                current = current.next;
            }
            System.out.println("null");
        }
        System.out.println();
    }
}