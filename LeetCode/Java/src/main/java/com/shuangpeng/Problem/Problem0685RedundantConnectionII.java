package com.shuangpeng.Problem;

import java.util.*;

public class Problem0685RedundantConnectionII {

    public int[] findRedundantDirectedConnection0(int[][] edges) {
        if (edges == null || edges.length < 3) {
            return new int[2];
        }
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] p = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            p[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (p[v] != v) {
                conflict = i;
            } else {
                if (find(parent, u) == find(parent, v)) {
                    cycle = i;
                } else {
                    p[v] = u;
                    union(parent, u, v);
                }
            }
        }
        if (conflict != -1) {
            if (cycle != -1) {
                return new int[]{p[edges[conflict][1]], edges[conflict][1]};
            } else {
                return new int[]{edges[conflict][0], edges[conflict][1]};
            }
        }
        return new int[]{edges[cycle][0], edges[cycle][1]};
    }

    private int find(int[] parent, int vertex) {
        int u = vertex;
        while (parent[u] != u) {
            parent[u] = parent[parent[u]];
            u = parent[u];
        }
        parent[vertex] = u;
        return u;
    }

    private void union(int[] parent, int u, int v) {
        parent[find(parent, u)] = find(parent, v);
    }



    class UnionFind {
        int[] ancestor;

        public UnionFind(int n) {
            ancestor = new int[n];
            for (int i = 0; i < n; ++i) {
                ancestor[i] = i;
            }
        }

        public void union(int index1, int index2) {
            ancestor[find(index1)] = find(index2);
        }

        public int find(int index) {
            if (ancestor[index] != index) {
                ancestor[index] = find(ancestor[index]);
            }
            return ancestor[index];
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }
    }
}
