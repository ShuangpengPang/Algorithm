package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1040MovingStonesUtilConsecutiveII（移动石子直到连续II）
 * @date 2023/4/7 10:20 AM
 */
public class Problem1040MovingStonesUtilConsecutiveII {

    public int[] numMovesStonesII0(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < n && j < n; i++) {
            while (j < n && stones[j] <= stones[i] + n - 1) {
                j++;
            }
            int c = j - i;
            if (c == n) {
                min = 0;
                break;
            }
            if (c == n - 1) {
                min = Math.min(min, stones[j - 1] == stones[i] + n - 1 ? 1 : 2);
            } else {
                min = Math.min(min, n - c);
            }
        }
        int max = stones[n - 1] - stones[0] + 2 - n - Math.min(stones[1] - stones[0], stones[n - 1] - stones[n - 2]);
        return new int[]{min, max};
    }

    public int[] numMovesStonesII1(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length, min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < n && j < n; i++) {
            while (j < n && stones[j] <= stones[i] + n - 1) {
                j++;
            }
            int count = n - (j - i);
            if (count == 1 && stones[j - 1] != stones[i] + n - 1) {
                count = 2;
            }
            min = Math.min(min, count);
        }
        return new int[]{min, Math.max(stones[n - 1] - stones[1], stones[n - 2] - stones[0]) - n + 2};
    }

    public int[] numMovesStonesII2(int[] stones) {
        int n = stones.length, min = n;
        Arrays.sort(stones);
        for (int i = 0, j = 0; j < n; i++) {
            while (j < n && stones[j] < stones[i] + n) {
                j++;
            }
            int cnt = n - (j - i);
            if (cnt == 1) {
                if (stones[i] + n - 1 == stones[j - 1]) {
                    min = Math.min(min, 1);
                }
            } else {
                min = Math.min(min, cnt);
            }
        }
        return new int[]{min, Math.max(stones[n - 1] - stones[1], stones[n - 2] - stones[0]) - n + 2};
    }

    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int s1 = stones[n - 1] - stones[1] + 1 - (n - 1);
        int s2 = stones[n - 2] - stones[0] + 1 - (n - 1);
        int max = Math.max(s1, s2);
        if (s1 == 0 || s2 == 0) {
            return new int[]{Math.min(max, 2), max};
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; j < n; i++) {
            while (j < n && stones[j] <= stones[i] + n - 1) {
                j++;
            }
            min = Math.min(min, n - (j - i));
        }
        return new int[]{min, max};
    }
}
