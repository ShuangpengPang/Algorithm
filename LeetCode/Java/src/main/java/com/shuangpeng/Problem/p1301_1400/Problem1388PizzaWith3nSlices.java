package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: Problem1388PizzaWith3nSlices（3n块披萨）
 * @Date 2022/8/10 2:28 PM
 * @Version 1.0
 */
public class Problem1388PizzaWith3nSlices {

    public int maxSizeSlices0(int[] slices) {
        int n = slices.length;
        int[] s1 = new int[n - 1], s2 = new int[n - 1];
        System.arraycopy(slices, 0, s1, 0, n - 1);
        System.arraycopy(slices, 1, s2, 0, n - 1);
        return Math.max(calculateSum(s1, n / 3), calculateSum(s2, n / 3));
    }

    private int calculateSum(int[] s, int cnt) {
        int n = s.length;
        int[][] dp = new int[n][cnt];
        dp[0][0] = s[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], s[i]);
            for (int j = 1; j <= Math.min(i, cnt - 1); j++) {
                if (i >= 2) {
                    dp[i][j] = dp[i - 2][j - 1] + s[i];
                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }
        return dp[n - 1][cnt - 1];
    }

    public int maxSizeSlices1(int[] slices) {
        int n = slices.length;
        // 使用数组模拟双向链表
        int[] linkL = new int[n];
        int[] linkR = new int[n];
        for (int i = 0; i < n; ++i) {
            linkL[i] = i == 0 ? n - 1 : i - 1;
            linkR[i] = i == n - 1 ? 0 : i + 1;
        }
        // 将初始的元素放入优先队列中
        boolean[] valid = new boolean[n];
        Arrays.fill(valid, true);
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] array1, int[] array2) {
                if (array1[0] != array2[0]) {
                    return array2[0] - array1[0];
                } else {
                    return array2[1] - array1[1];
                }
            }
        });
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{slices[i], i});
        }

        int ans = 0;
        for (int i = 0; i < n / 3; ++i) {
            // 从优先队列中取出元素时要判断其是否已被删除
            while (!valid[queue.peek()[1]]) {
                queue.poll();
            }
            int pos = queue.poll()[1];
            ans += slices[pos];
            // 更新当前位置的值
            slices[pos] = slices[linkL[pos]] + slices[linkR[pos]] - slices[pos];
            queue.offer(new int[]{slices[pos], pos});
            // 删去左右两侧的值
            valid[linkL[pos]] = valid[linkR[pos]] = false;
            // 修改双向链表
            linkR[linkL[linkL[pos]]] = pos;
            linkL[linkR[linkR[pos]]] = pos;
            linkL[pos] = linkL[linkL[pos]];
            linkR[pos] = linkR[linkR[pos]];
        }
        return ans;
    }

    public int maxSizeSlices(int[] slices) {
        int n = slices.length, m = n / 3;
        return Math.max(rangeSlice(slices, 0, n - 2, m), rangeSlice(slices, 1, n - 1, m));
    }

    public int rangeSlice(int[] slices, int start, int end, int m) {
        int n = end - start + 1, M = Integer.MIN_VALUE >> 1;
        int[] dp = new int[n + 1];
        dp[0] = M;
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i], slices[i + start]);
        }
        for (int i = 2; i <= m; i++) {
            for (int p1 = M, p2 = M, j = 1; j <= n; j++) {
                int t = dp[j];
                dp[j] = Math.max(dp[j - 1], p1 + slices[j + start - 1]);
                p1 = p2;
                p2 = t;
            }
        }
        return dp[n];
    }
}
