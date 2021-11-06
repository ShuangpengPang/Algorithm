package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Problem0706DesignHashMap {
    class MyHashMap {

        int capacity = 10009;
        List<List<int[]>> list;

        /** Initialize your data structure here. */
        public MyHashMap() {
            list = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                list.add(new ArrayList<>());
            }
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            List<int[]> array = list.get(hash(key));
            Iterator<int[]> iterator = array.iterator();
            while (iterator.hasNext()) {
                int[] pair = iterator.next();
                if (pair[0] == key) {
                    pair[1] = value;
                    return;
                }
            }
            array.add(new int[]{key, value});
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            List<int[]> array = list.get(hash(key));
            Iterator<int[]> iterator = array.iterator();
            while (iterator.hasNext()) {
                int[] pair = iterator.next();
                if (pair[0] == key) {
                    return pair[1];
                }
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            List<int[]> array = list.get(hash(key));
            Iterator<int[]> iterator = array.iterator();
            while (iterator.hasNext()) {
                int[] pair = iterator.next();
                if (pair[0] == key) {
                    iterator.remove();
                    return;
                }
            }
        }

        private int hash(int key) {
            return key % capacity;
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
