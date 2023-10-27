package com.shuangpeng.Problem.p1801_1900;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1882ProcessTasksUsingServers（使用服务器处理任务）
 * @date 2023/10/23 3:14 PM
 */
public class Problem1882ProcessTasksUsingServers {

    // 错误做法
    public int[] assignTasks00(int[] servers, int[] tasks) {
        PriorityQueue<Integer> serverQueue = new PriorityQueue<>((a, b) -> servers[a] != servers[b] ? servers[a] - servers[b] : a - b);
        PriorityQueue<int[]> taskQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int n = servers.length, m = tasks.length;
        for (int i = 0; i < n; i++) {
            serverQueue.offer(i);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int t = i;
            while (!taskQueue.isEmpty() && taskQueue.peek()[0] <= t) {
                serverQueue.offer(taskQueue.poll()[1]);
            }
            if (serverQueue.isEmpty()) {
                t = taskQueue.peek()[0];
                while (!taskQueue.isEmpty() && taskQueue.peek()[0] == t) {
                    serverQueue.offer(taskQueue.poll()[1]);
                }
            }
            ans[i] = serverQueue.poll();
            // 这里错误，时间不应该是t + tasks[i]
            taskQueue.offer(new int[]{t + tasks[i], ans[i]});
        }
        return ans;
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Integer> serverQueue = new PriorityQueue<>((a, b) -> servers[a] != servers[b] ? servers[a] - servers[b] : a - b);
        PriorityQueue<long[]> taskQueue = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        int n = servers.length, m = tasks.length;
        for (int i = 0; i < n; i++) {
            serverQueue.offer(i);
        }
        int[] ans = new int[m];
        long t = 0;
        for (int i = 0; i < m; i++) {
            if (t < i) {
                t = i;
            }
            while (!taskQueue.isEmpty() && taskQueue.peek()[0] <= t || serverQueue.isEmpty()) {
                long[] p = taskQueue.poll();
                t = p[0];
                serverQueue.offer((int) p[1]);
            }
            ans[i] = serverQueue.poll();
            taskQueue.offer(new long[]{t + tasks[i], ans[i]});
        }
        return ans;
    }
}
