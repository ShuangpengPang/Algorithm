package com.shuangpeng.Problem.p1701_1800;

import java.util.PriorityQueue;

public class Problem1705MaximumNumberOfEatenApples {

    public int eatenApples0(int[] apples, int[] days) {
        int n = apples.length;
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        for (int i = 0; i < n; ++i) {
            if (apples[i] > 0) {
                pq.offer(new int[]{apples[i], i + days[i]});
            }
            while (!pq.isEmpty() && pq.peek()[1] <= i) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                int[] pair = pq.poll();
                --pair[0];
                ++ans;
                if (pair[0] > 0) {
                    pq.offer(pair);
                }
            }
        }
        int day = n;
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[1] <= day) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                break;
            }
            int[] pair = pq.poll();
            int expire = Math.min(day + pair[0], pair[1]);
            ans += expire - day;
            day = expire;
        }
        return ans;
    }

    public int eatenApples(int[] apples, int[] days) {
        int lastDay = 0;
        int n = apples.length;
        for (int i = 0; i < n; ++i) {
            lastDay = Math.max(lastDay, i + days[i] - 1);
        }
        int[] rottenDay = new int[lastDay + 1];
        int start = lastDay, end = 0;
        int ans = 0;
        for (int i = 0; i <= lastDay; ++i) {
            if (i < n && apples[i] > 0) {
                rottenDay[i + days[i] - 1] += apples[i];
                start = Math.min(start, i + days[i] - 1);
                end = Math.max(end, i + days[i] - 1);
            }
            start = Math.max(start, i);
            while (start <= end) {
                if (rottenDay[start] > 0) {
                    --rottenDay[start];
                    ++ans;
                    break;
                }
                ++start;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1705MaximumNumberOfEatenApples a = new Problem1705MaximumNumberOfEatenApples();
//        int i = a.eatenApples(new int[]{3, 0, 0, 1, 2}, new int[]{6, 0, 0, 3, 2});
//        int j = 1;
//        // 1 2 3 5 8 13 21 34 55 89 144
//    }
}
