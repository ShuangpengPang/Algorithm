package com.shuangpeng.Problem.p3301_3400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3377DigitOperationsToMakeTwoIntegersEqual（使两个整数相等的数位操作）
 * @date 2025/4/6 21:31
 */
public class Problem3377DigitOperationsToMakeTwoIntegersEqual {

    private static final int N = (int) 1e5;
    private static final boolean[] visited = new boolean[N];

    static {
        visited[0] = visited[1] = true;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < N; i++) {
            if (!visited[i]) {
                primes.add(i);
            }
            for (int j : primes) {
                if ((long) j * i >= N) {
                    break;
                }
                visited[j * i] = true;
                if ((i % j) == 0) {
                    break;
                }
            }
        }
    }

    public int minOperations(int n, int m) {
        if (!visited[n] || !visited[m]) {
            return -1;
        }
        int M = getLength(n);
        int[] dis = new int[M];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[n] = n;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{dis[n], n});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int d = p[0], x = p[1];
            if (d > dis[x]) {
                continue;
            }
            if (x == m) {
                return d;
            }
            int u = 1, t = x;
            while (t != 0) {
                int y = t % 10;
                if (y < 9 && dis[x + u] > d + x + u && visited[x + u]) {
                    dis[x + u] = d + x + u;
                    pq.offer(new int[]{dis[x + u], x + u});
                }
                if ((y > 1 || y > 0 && t > 9) && dis[x - u] > d + x - u && visited[x - u]) {
                    dis[x - u] = d + x - u;
                    pq.offer(new int[]{dis[x - u], x - u});
                }
                t /= 10;
                u *= 10;
            }
        }
        return -1;
    }

    private int getLength(int n) {
        int x = 1;
        while (n != 0) {
            n /= 10;
            x *= 10;
        }
        return x;
    }
}
