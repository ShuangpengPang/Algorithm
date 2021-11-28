package com.shuangpeng.Problem.p0501_0600;

import java.util.*;

public class Problem0519RandomFlipMatrix {
    class Solution0 {

        int total;
        int m;
        int n;

        class Node {
            int index;
            int nextIndex;
            Node(int index) {
                this.index = index;
                this.nextIndex = index + 1;
            }
        }

        Map<Integer, Node> map;

        public Solution0(int m, int n) {
            this.m = m;
            this.n = n;
            this.total = m * n;
            map = new HashMap<>();
        }

        public int[] flip() {
            int index = (int) (Math.random() * total);
            --total;
            int nextIndex = dfs(index);
            return new int[]{nextIndex / n, nextIndex % n};
        }

        private int dfs(int index) {
            Node node = map.get(index);
            if (node == null) {
                node = new Node(index);
                map.put(index, node);
                return index;
            }
            node.nextIndex = dfs(node.nextIndex) + 1;
            return node.nextIndex - 1;
        }

        public void reset() {
            this.total = m * n;
            map.clear();
        }
    }

    class Solution {

        int total;
        int m;
        int n;

        Map<Integer, Integer> map;

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.total = m * n;
            map = new HashMap<>();
        }

        public int[] flip() {
            int index = (int) (Math.random() * total);
            --total;
            int i = map.getOrDefault(index, index);
            map.put(index, map.getOrDefault(total, total));
            return new int[]{i / n, i % n};
        }

        public void reset() {
            this.total = m * n;
            map.clear();
        }
    }

    class Solution1 {

        class Node {
            int count;
            Set<Integer> set;

            public Node(int count) {
                this.count = count;
                this.set = new HashSet<>();
            }
        }

        int m;
        int n;
        int total;
        int bucketSize;
        Node[] buckets;

        public Solution1(int m, int n) {
            this.m = m;
            this.n = n;
            this.total = m * n;
            this.bucketSize = (int) Math.sqrt(total) + 1;
            int size = total / bucketSize + 1;
            buckets = new Node[size];
            int count = 0;
            for (int i = 0; i < size; ++i) {
                int length = Math.min(bucketSize, total - count);
                buckets[i] = new Node(length);
                count += length;
            }
        }

        public int[] flip() {
            int num = (int) (Math.random() * total) + 1;
            --total;
            int count = 0;
            int i = 0;
            while (count + buckets[i].count < num) {
                count += buckets[i].count;
                ++i;
            }
            int remain = num - count;
            int start = i * bucketSize;
            int value = start;
            for (int j = 0; j < remain; ++j) {
                while (buckets[i].set.contains(start)) {
                    ++start;
                }
                value = start;
                ++start;
            }
            --buckets[i].count;
            buckets[i].set.add(value);
            return new int[]{value / n, value % n};
        }

        public void reset() {
            total = m * n;
            int count = 0;
            for (int i = 0; i < buckets.length; ++i) {
                int length = Math.min(bucketSize, total - count);
                buckets[i].count = length;
                buckets[i].set.clear();
                count += length;
            }
        }
    }
}

class Solution {

    int m;
    int n;
    int total;
    int bucketSize;
    List<Set<Integer>> buckets;
    Random random;

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.bucketSize = (int) Math.sqrt(total) + 1;
        this.buckets = new ArrayList<>();
        this.random = new Random();
        for (int i = 0; i < total; i += bucketSize) {
            buckets.add(new HashSet<>());
        }
    }

    public int[] flip() {
        int num = random.nextInt(total);
        --total;
        int size = buckets.size();
        int index = 0;
        int sumZero = 0;
        for (int i = 0; i < size; ++i) {
            Set<Integer> set = buckets.get(i);
            if (sumZero + bucketSize - set.size() > num) {
                for (int j = 0; j < bucketSize; ++j) {
                    if (!set.contains(index)) {
                        ++sumZero;
                        if (sumZero == num + 1) {
                            set.add(index);
                            return new int[]{index / n, index % n};
                        }
                    }
                    ++index;
                }
            }
            index += bucketSize;
            sumZero += bucketSize - set.size();
        }
        return null;
    }

    public void reset() {
        total = m * n;
        int size = buckets.size();
        for (int i = 0; i < size; ++i) {
            buckets.get(i).clear();
        }
    }
}
