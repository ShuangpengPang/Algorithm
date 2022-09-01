package com.shuangpeng.competition.双周赛.第057场双周赛;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem1942 {

    public int smallestChair0(int[][] times, int targetFriend) {
        int targetArri = times[targetFriend][0];
        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> leaveQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> indexQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        indexQueue.offer(0);
        for (int i = 0; i < times.length; i++) {
            while (!leaveQueue.isEmpty() && leaveQueue.peek()[0] <= times[i][0]) {
                indexQueue.offer(leaveQueue.poll()[1]);
            }
            int index = indexQueue.poll();
            if (indexQueue.isEmpty()) {
                indexQueue.offer(index + 1);
            }
            leaveQueue.offer(new int[]{times[i][1], index});
            if (times[i][0] == targetArri) {
                return index;
            }
        }
        return -1;
    }

    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] arrives = new int[n][2];
        int[][] leaves = new int[n][2];
        for (int i = 0; i < n; i++) {
            arrives[i][0] = times[i][0];
            arrives[i][1] = i;
            leaves[i][0] = times[i][1];
            leaves[i][1] = i;
        }
        Arrays.sort(arrives, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(leaves, Comparator.comparingInt(a -> a[0]));
        int[] seats = new int[n];
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        queue.offer(0);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (leaves[j][0] <= arrives[i][0]) {
                queue.offer(seats[leaves[j][1]]);
                j++;
            }
            int index = queue.poll();
            if (queue.isEmpty()) {
                queue.offer(index + 1);
            }
            seats[arrives[i][1]] = index;
            if (arrives[i][1] == targetFriend) {
                return index;
            }
        }
        return -1;
    }
}
