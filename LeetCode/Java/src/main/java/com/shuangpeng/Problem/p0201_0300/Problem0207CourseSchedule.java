package com.shuangpeng.Problem.p0201_0300;

import java.util.*;

/**
 * @description:（课程表）
 * @date 2023/9/9 12:02 PM
 **/
public class Problem0207CourseSchedule {

    // 深度优先
    public boolean canFinish0(int numCourses, int[][] prerequisites) {
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


    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    // 深度优先（官方）
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    // 深度优先（哈希表）
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edges = prerequisites[i];
            int start = edges[1];
            int end = edges[0];
            if (map.containsKey(start)) {
                map.get(start).add(end);
            } else {
                List<Integer> list = new ArrayList<>(2);
                list.add(end);
                map.put(start, list);
            }
        }
        int[] visited = new int[numCourses];
        Set<Integer> keySet = map.keySet();
        for (int key : keySet) {
            if (visited[key] == 0 && !dfs(map, key, visited)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(Map<Integer, List<Integer>> map, int start, int[] visited) {
        visited[start] = 1;
        if (map.containsKey(start)) {
            List<Integer> list = map.get(start);
            for (int i : list) {
                if (visited[i] == 0 && !dfs(map, i, visited)) {
                    return false;
                } else if (visited[i] == 1) {
                    return false;
                } else if (visited[i] == 2) {
                    continue;
                }
            }
        }
        visited[start] = 2;
        return true;
    }


    // 深度优先（邻接表）
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int start = edge[1];
            int end = edge[0];
            edges.get(start).add(end);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && !dfs(edges, i, visited)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(List<List<Integer>> edges, int start, int[] visited) {
        visited[start] = 1;
        List<Integer> list = edges.get(start);
        for (int i : list) {
            if (visited[i] == 0 && !dfs(edges, i, visited)) {
                return false;
            } else if (visited[i] == 1) {
                return false;
            } else if (visited[i] == 2) {
                continue;
            }
        }
        visited[start] = 2;
        return true;
    }

    // 广度优先
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        List<List<Integer>> edges = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int start = edge[1];
            int end = edge[0];
            indegree[end]++;
            edges.get(start).add(end);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            List<Integer> list = edges.get(vertex);
            for (int edge : list) {
                indegree[edge]--;
                if (indegree[edge] == 0) {
                    queue.offer(edge);
                }
            }
            count++;
        }
        return count == numCourses;
    }
}

class Problem0207CourseSchedule0 {

    public boolean canFinish0(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<Integer>[] graph = new List[numCourses];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
            graph[p[1]].add(p[0]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[numCourses];
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                visited[i] = true;
                cnt++;
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : graph[x]) {
                if (visited[y]) {
                    return false;
                }
                if (--inDegree[y] == 0) {
                    visited[y] = true;
                    q.offer(y);
                    cnt++;
                }
            }
        }
        return cnt == numCourses;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length, idx = 0;
        int[] e = new int[n], ne = new int[n], h = new int[numCourses];
        Arrays.fill(h, -1);
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            e[idx] = p[0];
            ne[idx] = h[p[1]];
            h[p[1]] = idx++;
            inDegree[p[0]]++;
        }
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                cnt++;
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = h[x]; i != -1; i = ne[i]) {
                int y = e[i];
                if (--inDegree[y] == 0) {
                    q.offer(y);
                    cnt++;
                }
            }
        }
        return cnt == numCourses;
    }
}

class Problem0207CourseSchedule1 {

    int[] e, ne, h;
    int idx;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        e = new int[n];
        ne = new int[n];
        h = new int[numCourses];
        Arrays.fill(h, -1);
        idx = 0;
        for (int[] p : prerequisites) {
            e[idx] = p[0];
            ne[idx] = h[p[1]];
            h[p[1]] = idx++;
        }
        int[] state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && !dfs(i, state)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int x, int[] state) {
        if (state[x] == 1) {
            return false;
        }
        if (state[x] == 2) {
            return true;
        }
        state[x] = 1;
        for (int i = h[x]; i != -1; i = ne[i]) {
            if (!dfs(e[i], state)) {
                return false;
            }
        }
        state[x] = 2;
        return true;
    }
}
