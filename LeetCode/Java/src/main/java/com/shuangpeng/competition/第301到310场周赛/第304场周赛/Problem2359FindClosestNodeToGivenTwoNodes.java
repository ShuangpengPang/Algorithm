package com.shuangpeng.competition.第301到310场周赛.第304场周赛;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: Problem2359FindClosestNodeToGivenTwoNodes（找到离给定两个节点最近的节点）
 * @Date 2022/8/6 5:35 PM
 * @Version 1.0
 */
public class Problem2359FindClosestNodeToGivenTwoNodes {

    // 比赛时写法
    public int closestMeetingNode0(int[] edges, int node1, int node2) {
        Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        int n = node1, s = 0;
        while (n != -1 && !map1.containsKey(n)) {
            map1.put(n, s);
            n = edges[n];
            s++;
        }
        n = node2;
        s = 0;
        while (n != -1 && !map2.containsKey(n)) {
            map2.put(n, s);
            n = edges[n];
            s++;
        }
        if (map1.containsKey(node2) && map2.containsKey(node1)) {
            if (map1.get(node2) < map2.get(node1)) {
                return node2;
            } else if (map1.get(node2) > map2.get(node1)) {
                return node1;
            } else {
                return Math.min(node1, node2);
            }
        }
        Set<Integer> set1 = new HashSet<>();
        int n1 = node2;
        while (n1 != -1 && !map1.containsKey(n1) && !set1.contains(n1)) {
            set1.add(n1);
            n1 = edges[n1];
        }
        if (!map1.containsKey(n1)) {
            return -1;
        }
        Set<Integer> set2 = new HashSet<>();
        int n2 = node1;
        while (n2 != -1 && !map2.containsKey(n2) && !set2.contains(n2)) {
            set2.add(n2);
            n2 = edges[n2];
        }
        if (n1 == n2) {
            return n1;
        }
        int max1 = Math.max(map1.get(n1), map2.get(n1)), max2 = Math.max(map1.get(n2), map2.get(n2));
        if (max1 < max2) {
            return n1;
        } else if (max1 > max2) {
            return n2;
        } else {
            return Math.min(n1, n2);
        }
    }

    public int closestMeetingNode1(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dis1 = new int[n], dis2 = new int[n];
        for (int i = node1, d = 0; i != -1 && dis1[i] == 0; i = edges[i]) {
            dis1[i] = ++d;
        }
        for (int i = node2, d = 0; i != -1 && dis2[i] == 0; i = edges[i]) {
            dis2[i] = ++d;
        }
        int ans = -1, dis = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dis1[i] != 0 && dis2[i] != 0) {
                int d = Math.max(dis1[i], dis2[i]);
                if (d < dis) {
                    dis = d;
                    ans = i;
                }
            }
        }
        return ans;
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        boolean[] vis1 = new boolean[n], vis2 = new boolean[n];
        while (true) {
            int min = Integer.MAX_VALUE;
            if (node1 != -1 && !vis1[node1]) {
                vis1[node1] = true;
                if (vis2[node1]) {
                    min = Math.min(min, node1);
                }
                node1 = edges[node1];
            }
            if (node2 != -1 && !vis2[node2]) {
                vis2[node2] = true;
                if (vis1[node2]) {
                    min = Math.min(min, node2);
                }
                node2 = edges[node2];
            }
            if (min != Integer.MAX_VALUE) {
                return min;
            }
            if ((node1 == -1 || vis1[node1]) && (node2 == -1 || vis2[node2])) {
                break;
            }
        }
        return -1;
    }
}