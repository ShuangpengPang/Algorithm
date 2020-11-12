package com.shuangpeng;

import java.util.*;

public class Problem0207CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int start = prerequisites[i][0];
            int end = prerequisites[i][1];
            if (map.containsKey(start)) {
                List<Integer> list = map.get(start);
                list.add(end);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(end);
                map.put(start, list);
            }
        }
        boolean[] visited = new boolean[numCourses];
        Set<Integer> result = new HashSet<>();
        Set<Integer> keySet = map.keySet();
        for (int vertex : keySet) {
            if (!visited[vertex] && !dfs(map, vertex, new HashSet<>(), visited, result)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(Map<Integer, List<Integer>> map, int vertex, Set<Integer> set,
                       boolean[] visited, Set<Integer> result) {
        if (set.contains(vertex)) {
            return false;
        }
        if (!map.containsKey(vertex) || result.contains(vertex)) {
            return true;
        }
        visited[vertex] = true;
        set.add(vertex);
        List<Integer> list = map.get(vertex);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!dfs(map, list.get(i), set, visited, result)) {
                return false;
            }
        }
        set.remove(vertex);
        result.add(vertex);
        return true;
    }
}
