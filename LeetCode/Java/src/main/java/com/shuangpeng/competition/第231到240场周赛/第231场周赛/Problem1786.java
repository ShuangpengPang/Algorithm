package com.shuangpeng.competition.第231到240场周赛.第231场周赛;

import java.util.*;

public class Problem1786 {

    public int countRestrictedPaths0(int n, int[][] edges) {
        if (n == 1) {
            return 1;
        }
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph.get(u).add(new int[]{v, edge[2]});
            graph.get(v).add(new int[]{u, edge[2]});
        }
        int[] minWeight = new int[n];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        minWeight[n - 1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n - 1);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            int weight = minWeight[u];
            List<int[]> neighbor = graph.get(u);
            for (int[] info : neighbor) {
                int v = info[0];
                int w = weight + info[1];
                if (w < minWeight[v]) {
                    minWeight[v] = w;
                    queue.offer(v);
                }
            }
        }
        return backtrack(0, n - 1, graph, minWeight, new int[n]);
    }

    private int backtrack(int u, int n, List<List<int[]>> graph, int[] minWeight, int[] map) {
        if (u == n) {
            return 1;
        }
        if (map[u] > 0) {
            return map[u];
        }
        int paths = 0;
        List<int[]> neighbors = graph.get(u);
        for (int[] neighbor : neighbors) {
            int v = neighbor[0];
            if (minWeight[v] < minWeight[u]) {
                paths += backtrack(v, n, graph, minWeight, map);
                paths = paths % 1000000007;
            }
        }
        map[u] = paths;
        return paths;
    }

    public int countRestrictedPaths1(int n, int[][] edges) {
        int cnt = 0;
        Map<Integer, List<int[]>> map = new HashMap<>();
        // 初始化邻接表
        for (int[] t : edges) {
            int x = t[0];
            int y = t[1];
            map.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{y, t[2]});
            map.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, t[2]});
        }

        // 保存到n点的 最短距离 和 受限路径数
        int[] distance = findShortPath(map,n,n);
        Long[] mem = new Long[n + 1];

        cnt = (int)findLimitedPathCount(map,1,n,distance,mem);
        return cnt;
    }

    private long findLimitedPathCount(Map<Integer, List<int[]>> map, int i, int n, int[] distance, Long[] mem) {
        if(mem[i]!=null)return mem[i];
        if(i==n)return 1;
        long cnt = 0;
        List<int[]> list = map.getOrDefault(i,Collections.emptyList());
        for (int[] arr:list){
            int next = arr[0];
            //如果相邻节点距离比当前距离小，说明是受限路径
            if(distance[next] < distance[i]){
                cnt += findLimitedPathCount(map,next,n,distance,mem);
                cnt %= MOD;
            }
        }
        mem[i] = cnt;
        return cnt;
    }


    public int[] findShortPath( Map<Integer, List<int[]>> map, int n, int start) {
        // 初始化distance数组和visit数组，并用最大值填充作为非连接状态INF
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visit = new boolean[n + 1];

        // 初始化，索引0和起点的distance为0
        distance[start] = 0;
        distance[0] = 0;

        // 堆优化，将距离作为排序标准。单独用传入距离是因为PriorityQueue的上浮规则决定
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        // 把起点放进去，距离为0
        queue.offer(new int[]{start,0});

        while (!queue.isEmpty()) {
            // 当队列不空，拿出一个源出来
            Integer poll = queue.poll()[0];
            if(visit[poll]) continue;
            // 标记访问
            visit[poll] = true;
            // 遍历它的相邻节点
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                if (visit[next]) continue;
                // 更新到这个相邻节点的最短距离，与 poll出来的节点增加的距离 比较
                distance[next] = Math.min(distance[next], distance[poll] + arr[1]);
                //堆中新增节点，这里需要手动传入 next节点堆距离值。否则如果next在队列中，将永远无法上浮。
                queue.offer(new int[]{next,distance[next]});
            }
        }
        return distance;
    }

    final int MOD = 1000000007;

    public int countRestrictedPaths(int n, int[][] edges) {
        if (n <= 1) {
            return 1;
        }
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            int w = edge[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[n - 1] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.offer(new int[]{n - 1, 0});
        while (!queue.isEmpty()) {
            int[] array = queue.poll();
            int u = array[0];
            if (array[1] > distance[u]) {
                continue;
            }
            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int d = array[1] + edge[1];
                if (d < distance[v]) {
                    distance[v] = d;
                    queue.offer(new int[]{v, d});
                }
            }
        }
        return findLimitPath(distance, 0, n - 1, new int[n], graph);
    }

    private int findLimitPath(int[] distance, int u, int n, int[] map, List<List<int[]>> graph) {
        if (u == n) {
            return 1;
        }
        if (map[u] > 0) {
            return map[u];
        }
        int paths = 0;
        for (int[] edge : graph.get(u)) {
            int v = edge[0];
            if (distance[v] < distance[u]) {
                paths += findLimitPath(distance, v, n, map, graph);
                paths %= 1000000007;
            }
        }
        map[u] = paths;
        return paths;
    }
}
