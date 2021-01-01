package com.shuangpeng.Problem;

import java.util.*;

public class Problem0210CourseScheduleII {

    public int[] findOrder0(int numCourses, int[][] prerequisites) {
        Set<Integer> zeroSet = new HashSet<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            zeroSet.add(i);
        }
        Map<Integer, List<Integer>> map = new HashMap<>(numCourses);
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            if (!map.containsKey(prerequisites[i][1])) {
                map.put(prerequisites[i][1], new ArrayList<>());
            }
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
            zeroSet.remove(prerequisites[i][0]);
        }
        List<Integer> list = new ArrayList<>(numCourses);
        while (!zeroSet.isEmpty()) {
            int course = zeroSet.iterator().next();
            zeroSet.remove(course);
            if (map.containsKey(course)) {
                List<Integer> next = map.get(course);
                map.remove(course);
                int size = next.size();
                for (int i = 0; i < size; i++) {
                    int nextCourse = next.get(i);
                    indegree[nextCourse]--;
                    if (indegree[nextCourse] == 0) {
                        zeroSet.add(nextCourse);
                    }
                }
            }
            list.add(course);
        }
        if (list.size() == numCourses) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = list.get(i);
            }
            return result;
        } else {
            return new int[0];
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int[] answer = new int[numCourses];
        index = numCourses - 1;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, edges, answer);
            }
        }
        if (valid) {
            return answer;
        }
        return new int[0];
    }

    boolean valid = true;
    int index = 0;
    public void dfs(int u, int[] visited, List<List<Integer>> edges, int[] answer) {
        visited[u] = 1;
        List<Integer> neighbors = edges.get(u);
        int size = neighbors.size();
        for (int i = 0; i < size; i++) {
            if (!valid) {
                return;
            }
            int v = neighbors.get(i);
            if (visited[v] == 1) {
                valid = false;
                return;
            }
            if (visited[v] == 0) {
                dfs(v, visited, edges, answer);
            }
        }
        answer[index--] = u;
        visited[u] = 2;
    }
}
