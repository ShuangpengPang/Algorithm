package com.shuangpeng.competition.第269场周赛;

import java.util.*;

public class Probem2092 {

    public List<Integer> findAllPeople0(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] meeting : meetings) {
            int u = meeting[0], v = meeting[1];
            map.putIfAbsent(u, new ArrayList<>());
            map.putIfAbsent(v, new ArrayList<>());
            map.get(u).add(meeting);
            map.get(v).add(meeting);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int[] meeting : map.getOrDefault(0, new ArrayList<>())) {
            pq.offer(meeting);
        }
        for (int[] meeting : map.getOrDefault(firstPerson, new ArrayList<>())) {
            pq.offer(meeting);
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
        parent[firstPerson] = 0;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(firstPerson);
        for (int[] meeting : meetings) {
            int u = meeting[0], v = meeting[1], t = meeting[2];
            while (!pq.isEmpty() && pq.peek()[2] <= t) {
                int[] tuple = pq.poll();
                addMeeting(parent, tuple[0], t, map, pq, ans);
                addMeeting(parent, tuple[1], t, map, pq, ans);
            }
            if (parent[u] == 0 && parent[v] != 0) {
                for (int[] m : map.getOrDefault(v, new ArrayList<>())) {
                    if (m[2] >= t) {
                        pq.offer(m);
                    }
                }
            } else if (parent[u] != 0 && parent[v] == 0) {
                for (int[] m : map.getOrDefault(u, new ArrayList<>())) {
                    if (m[2] >= t) {
                        pq.offer(m);
                    }
                }
            }
        }
        return ans;
    }

    private void addMeeting(int[] parent, int x, int time, Map<Integer, List<int[]>> map, PriorityQueue<int[]> pq, List<Integer> ans) {
        if (parent[x] != 0) {
            parent[x] = 0;
            ans.add(x);
            for (int[] m : map.getOrDefault(x, new ArrayList<>())) {
                if (m[2] >= time && (parent[m[0]] != 0 || parent[m[1]] != 0)) {
                    pq.offer(m);
                }
            }
        }
    }

    public List<Integer> findAllPeople1(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[2]));
        boolean[] secret = new boolean[n];
        secret[0] = secret[firstPerson] = true;
        int m = meetings.length;
        int i = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        while (i < m) {
            int j = i;
            while (j < m && meetings[j][2] == meetings[i][2]) {
                ++j;
            }
            map.clear();
            set.clear();
            for (int k = i; k < j; ++k) {
                int u = meetings[k][0], v = meetings[k][1];
                map.putIfAbsent(u, new ArrayList<>());
                map.putIfAbsent(v, new ArrayList<>());
                map.get(u).add(v);
                map.get(v).add(u);
                if (!set.contains(u)) {
                    set.add(u);
                }
                if (!set.contains(v)) {
                    set.add(v);
                }
            }
            queue.clear();
            for (int u : set) {
                if (secret[u]) {
                    queue.offer(u);
                }
            }
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : map.get(u)) {
                    if (!secret[v]) {
                        secret[v] = true;
                        queue.offer(v);
                    }
                }
            }
            i = j;
        }
        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            if (secret[j]) {
                ans.add(j);
            }
        }
        return ans;
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int[] m : meetings) {
            map.putIfAbsent(m[2], new ArrayList<>());
            map.get(m[2]).add(m);
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
        parent[firstPerson] = 0;
        for (List<int[]> value : map.values()) {
            for (int[] m : value) {
                int u = findParent(parent, m[0]), v = findParent(parent, m[1]);
                if (u == 0 || v == 0) {
                    parent[u] = 0;
                    parent[v] = 0;
                }
                parent[u] = parent[v];
            }
            for (int[] m : value) {
                int u = m[0], v = m[1];
                if (findParent(parent, u) == 0) {
                    parent[u] = 0;
                    parent[v] = 0;
                } else {
                    parent[u] = u;
                    parent[v] = v;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (findParent(parent, i) == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int findParent(int[] parent, int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
