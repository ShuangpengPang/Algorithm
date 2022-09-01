package com.shuangpeng.competition.第261到270场周赛.第267场周赛;

import java.util.*;

public class Problem2076ProcessRestrictedFriendRequests {

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        Map<Integer, Set<Integer>> mutex = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            mutex.put(i, new HashSet<>());
        }
        for (int[] restriction : restrictions) {
            int u = restriction[0], v = restriction[1];
            mutex.get(u).add(v);
            mutex.get(v).add(u);
        }
        int[] parent = new int[n];
        int[] size = new int[n];
        Map<Integer, Set<Integer>> members = new HashMap<>();
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            members.put(i, new HashSet<>());
            members.get(i).add(i);
        }
        int m = requests.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; ++i) {
            int[] request = requests[i];
            int u = request[0], v = request[1];
            int parentU = findParent(parent, u);
            int parentV = findParent(parent, v);
            if (parentU == parentV) {
                ans[i] = true;
            } else if (check(mutex, members, parentU, parentV)) {
                ans[i] = true;
                union(parent, size, parentU, parentV, mutex, members);
            }
        }
        return ans;
    }

    private void union(int[] parent, int[] size, int u, int v, Map<Integer, Set<Integer>> mutex, Map<Integer, Set<Integer>> members) {
        if (size[u] >= size[v]) {
            size[u] += size[v];
            parent[v] = u;
            mutex.get(u).addAll(mutex.get(v));
            members.get(u).addAll(members.get(v));
        } else {
            size[v] += size[u];
            parent[u] = v;
            mutex.get(v).addAll(mutex.get(u));
            members.get(v).addAll(members.get(u));
        }
    }

    private int findParent(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    private boolean check(Map<Integer, Set<Integer>> mutex, Map<Integer, Set<Integer>> members, int u, int v) {
        Set<Integer> mt = mutex.get(u);
        Set<Integer> mb = members.get(v);
        if (mutex.get(u).size() > mutex.get(v).size()) {
            mt = mutex.get(v);
            mb = members.get(u);
        }
        for (int m : mt) {
            if (mb.contains(m)) {
                return false;
            }
        }
        return true;
    }
}
