package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Problem0705DesignHashSet {

    class MyHashSet {

        List<Integer>[] set;
        int capacity = 1009;

        /** Initialize your data structure here. */
        public MyHashSet() {
            set = new List[capacity];
        }

        public void add(int key) {
            int mod = key % capacity;
            if (set[mod] == null) {
                set[mod] = new ArrayList<>();
            }
            if (set[mod].contains(key)) {
                return;
            }
            set[mod].add(key);
        }

        public void remove(int key) {
            int mod = key % capacity;
            if (set[mod] != null) {
                set[mod].remove((Object) key);
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int mod = key % capacity;
            return set[mod] != null && set[mod].contains(key);
        }
    }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
}

class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}

class MyHashSet0 {
    int[] bs = new int[40000];
    public void add(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        setVal(bucketIdx, bitIdx, true);
    }

    public void remove(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        setVal(bucketIdx, bitIdx, false);
    }

    public boolean contains(int key) {
        int bucketIdx = key / 32;
        int bitIdx = key % 32;
        return getVal(bucketIdx, bitIdx);
    }

    void setVal(int bucket, int loc, boolean val) {
        if (val) {
            int u = (bs[bucket] | (1 << loc));
            bs[bucket] = u;
        } else {
            int u = bs[bucket] & ~(1 << loc);
            bs[bucket] = u;
        }
    }

    boolean getVal(int bucket, int loc) {
        int u = (bs[bucket] >> loc) & 1;
        return u == 1;
    }
}
