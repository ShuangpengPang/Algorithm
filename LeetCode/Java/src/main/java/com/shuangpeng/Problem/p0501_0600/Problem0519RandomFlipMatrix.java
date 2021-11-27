package com.shuangpeng.Problem.p0501_0600;

import java.util.HashMap;
import java.util.Map;

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
