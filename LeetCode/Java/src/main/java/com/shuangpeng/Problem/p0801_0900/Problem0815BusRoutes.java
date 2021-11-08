package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0815BusRoutes {

    public int numBusesToDestination0(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        boolean[] visited = new boolean[n];
        Queue<Set<Integer>> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            boolean find = false;
            for (int s : routes[i]) {
                if (s == source) {
                    find = true;
                    break;
                }
            }
            if (find) {
                visited[i] = true;
                Set<Integer> set = new HashSet<>();
                addToSet(set, routes[i]);
                if (set.contains(target)) {
                    return 1;
                }
                queue.offer(set);
            }
        }
        if (queue.isEmpty()) {
            return -1;
        }
        int step = 1;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Set<Integer> set = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        boolean find = false;
                        for (int k : routes[j]) {
                            if (set.contains(k)) {
                                find = true;
                                break;
                            }
                        }
                        if (find) {
                            visited[j] = true;
                            Set<Integer> newSet = new HashSet<>(set);
                            addToSet(newSet, routes[j]);
                            if (newSet.contains(target)) {
                                return step;
                            }
                            queue.offer(newSet);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private void addToSet(Set<Integer> set, int[] array) {
        for (int i : array) {
            set.add(i);
        }
    }

    public int numBusesToDestination1(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<Integer>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new LinkedList<Integer>();
        for (int bus : rec.getOrDefault(source, new ArrayList<Integer>())) {
            dis[bus] = 1;
            que.offer(bus);
        }
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    que.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int bus : rec.getOrDefault(target, new ArrayList<Integer>())) {
            if (dis[bus] != -1) {
                ret = Math.min(ret, dis[bus]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public int numBusesToDestination2(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        boolean[][] edges = new boolean[n][n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int s : routes[i]) {
                Set<Integer> set = map.getOrDefault(s, new HashSet<>());
                for (int b : set) {
                    edges[i][b] = edges[b][i] = true;
                }
                set.add(i);
                map.put(s, set);
            }
        }
        if (!map.containsKey(source) || !map.containsKey(target)) {
            return -1;
        }
        Set<Integer> targetSet = map.get(target);
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int b : map.get(source)) {
            if (targetSet.contains(b)) {
                return 1;
            }
            visited[b] = true;
            queue.offer(b);
        }
        int count = 1;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int j = queue.poll();
                for (int k = 0; k < n; k++) {
                    if (edges[j][k] && !visited[k]) {
                        if (targetSet.contains(k)) {
                            return count;
                        }
                        visited[k] = true;
                        queue.offer(k);
                    }
                }
            }
        }
        return -1;
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        boolean[][] edges = new boolean[n][n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int s : routes[i]) {
                List<Integer> buses = map.getOrDefault(s, new ArrayList<>());
                for (int bus : buses) {
                    edges[i][bus] = edges[bus][i] = true;
                }
                buses.add(i);
                map.put(s, buses);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];
        for (int bus : map.getOrDefault(source, new ArrayList<>())) {
            distance[bus] = 1;
            queue.offer(bus);
        }
        while (!queue.isEmpty()) {
            int bus = queue.poll();
            for (int i = 0; i < n; i++) {
                if (edges[bus][i] && distance[i] == 0) {
                    distance[i] = distance[bus] + 1;
                    queue.offer(i);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int bus : map.getOrDefault(target, new ArrayList<>())) {
            if (distance[bus] != 0) {
                answer = Math.min(answer, distance[bus]);
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

//    public static void main(String[] args) {
//        Problem0815BusRoutes a = new Problem0815BusRoutes();
//        int[][] routes = {{10,13,22,28,32,35,43},{2,11,15,25,27},{6,13,18,25,42},{5,6,20,27,37,47},{7,11,19,23,35},{7,11,17,25,31,43,46,48},{1,4,10,16,25,26,46},{7,11},{3,9,19,20,21,24,32,45,46,49},{11,41}};
//        a.numBusesToDestination(routes, 37, 43);
//    }
}
