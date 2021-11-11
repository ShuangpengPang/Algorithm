package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem1834SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] copy = new int[n][3];
        for (int i = 0; i < n; i++) {
            copy[i][0] = tasks[i][0];
            copy[i][1] = tasks[i][1];
            copy[i][2] = i;
        }
        Arrays.sort(copy, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        int[] answer = new int[n];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        int time = 0;
        int i = 0;
        int index = 0;
        while (!queue.isEmpty() || i < n) {
            if (queue.isEmpty()) {
                time = copy[i][0];
                queue.offer(copy[i++]);
            }
            int[] task = queue.poll();
            answer[index++] = task[2];
            time += task[1];
            while (i < n && copy[i][0] <= time) {
                queue.offer(copy[i++]);
            }
        }
        return answer;
    }
}
