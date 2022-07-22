package com.shuangpeng.Problem.p1201_1300;

import java.util.*;

/**
 * @Description: Problem1203SortItemsByGroupsRespectingDependencies（项目管理）
 * @Date 2022/7/21 5:44 PM
 * @Version 1.0
 */
public class Problem1203SortItemsByGroupsRespectingDependencies {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        List<Set<Integer>> groupList = new ArrayList<>();
        Map<Integer, Integer> groupIds = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
            if (groupIds.containsKey(group[i])) {
                groupList.get(groupIds.get(group[i])).add(i);
            } else {
                groupIds.put(group[i], groupList.size());
                Set<Integer> items = new HashSet<>();
                items.add(i);
                groupList.add(items);
            }
        }
        int size = groupList.size();
        Set<Integer>[] beforeGroup = new Set[size];
        Arrays.setAll(beforeGroup, e -> new HashSet<>());
        for (int i = 0; i < n; i++) {
            for (int j : beforeItems.get(i)) {
                if (group[i] != group[j]) {
                    int g1 = groupIds.get(group[i]), g2 = groupIds.get(group[j]);
                    beforeGroup[g1].add(g2);
                    if (beforeGroup[g2].contains(g1)) {
                        return new int[0];
                    }
                }
            }
        }
        int[] groups = sortGroup(beforeGroup);
        if (groups == null) {
            return new int[0];
        }
        int[] ans = new int[n];
        int idx = 0;
        for (int g : groups) {
            int[] arr = sortItem(groupList.get(g), beforeItems);
            if (arr == null) {
                return new int[0];
            }
            for (int num : arr) {
                ans[idx++] = num;
            }
        }
        return ans;
    }
    
    private int[] sortItem(Set<Integer> set, List<List<Integer>> beforeItems) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int i : set) {
            for (int j : beforeItems.get(i)) {
                if (set.contains(j)) {
                    inDegree.put(j, inDegree.getOrDefault(j, 0) + 1);
                }
            }
        }
        int n = set.size();
        int[] ans = new int[n];
        int idx = n - 1;
        Queue<Integer> q = new LinkedList<>();
        for (int i : set) {
            if (!inDegree.containsKey(i)) {
                q.offer(i);
                ans[idx--] = i;
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : beforeItems.get(i)) {
                int count = inDegree.getOrDefault(j, 0) - 1;
                if (count >= 0) {
                    inDegree.put(j, count);
                    if (count == 0) {
                        ans[idx--] = j;
                        q.offer(j);
                    }
                }
            }
        }
        if (idx >= 0) {
            return null;
        }
        return ans;
    }

    private int[] sortGroup(Set<Integer>[] sets) {
        int n = sets.length;
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j : sets[i]) {
                inDegree[j]++;
            }
        }
        int[] ans = new int[n];
        int idx = n - 1;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                ans[idx--] = i;
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : sets[i]) {
                inDegree[j]--;
                if (inDegree[j] == 0) {
                    ans[idx--] = j;
                    q.offer(j);
                }
            }
        }
        if (idx >= 0) {
            return null;
        }
        return ans;
    }
}
