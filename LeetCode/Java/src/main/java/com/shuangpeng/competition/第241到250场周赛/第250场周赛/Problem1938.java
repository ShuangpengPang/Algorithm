package com.shuangpeng.competition.第241到250场周赛.第250场周赛;

import java.util.ArrayList;
import java.util.List;

public class Problem1938 {

    class TrieNode {
        int count;
        TrieNode[] nodes;

        public TrieNode() {
            count = 0;
            nodes = new TrieNode[2];
        }
    }

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length, size = queries.length;
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int rootData = -1;
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                rootData = i;
            } else {
                edges[parents[i]].add(i);
            }
        }
        List<List<int[]>> queryList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            queryList.add(new ArrayList<>());
        }
        int maxValue = n - 1;
        for (int i = 0; i < size; i++) {
            queryList.get(queries[i][0]).add(new int[]{i, queries[i][1]});
            maxValue = Math.max(maxValue, queries[i][1]);
        }
        int bits = 0;
        while (maxValue > 0) {
            bits++;
            maxValue >>= 1;
        }
        int[] answer = new int[size];
        dfs(edges, rootData, new TrieNode(), bits, queryList, answer);
        return answer;
    }

    private void dfs(List<Integer>[] edges, int u, TrieNode root, int bits,
                     List<List<int[]>> queryList, int[] answer) {
        add(root, u, bits);
        for (int[] pair : queryList.get(u)) {
            int value = pair[1];
            int maxValue = query(root, value, bits);
            answer[pair[0]] = value ^ maxValue;
        }
        for (int v : edges[u]) {
            dfs(edges, v, root, bits, queryList, answer);
        }
        erase(root, u, bits);
    }

    private int query(TrieNode root, int value, int bits) {
        int maxValue = 0;
        int b = bits - 1;
        TrieNode node = root;
        while (b >= 0) {
            int v = 1 << b;
            if ((value & v) == 0) {
                if (node.nodes[1] != null && node.nodes[1].count > 0) {
                    maxValue += v;
                    node = node.nodes[1];
                } else {
                    node = node.nodes[0];
                }
            } else {
                if (node.nodes[0] != null && node.nodes[0].count > 0) {
                    node = node.nodes[0];
                } else {
                    maxValue += v;
                    node = node.nodes[1];
                }
            }
            b--;
        }
        return maxValue;
    }

    private void add(TrieNode root, int data, int bits) {
        int b = bits - 1;
        TrieNode node = root;
        while (b >= 0) {
            if ((data & (1 << b)) != 0) {
                if (node.nodes[1] == null) {
                    node.nodes[1] = new TrieNode();
                }
                node = node.nodes[1];
            } else {
                if (node.nodes[0] == null) {
                    node.nodes[0] = new TrieNode();
                }
                node = node.nodes[0];
            }
            node.count++;
            b--;
        }
    }

    private void erase(TrieNode root, int data, int bits) {
        int b = bits - 1;
        TrieNode node = root;
        while (b >= 0) {
            if ((data & (1 << b)) != 0) {
                node = node.nodes[1];
            } else {
                node = node.nodes[0];
            }
            node.count--;
            b--;
        }
    }

//    public static void main(String[] args) {
//        Problem1938 a = new Problem1938();
//        int[] parents = {-1, 0, 0, 0, 3};
//        int[][] queries = {{4, 6}, {0, 0}, {0, 3}, {1, 8}, {4, 0}};
//        a.maxGeneticDifference(parents, queries);
//    }
}
