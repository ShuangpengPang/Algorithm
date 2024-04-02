package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3096MinimumLevelsToGainMorePoints（得到更多分数的最少关卡数目）
 * @date 2024/4/2 11:08 AM
 */
public class Problem3096MinimumLevelsToGainMorePoints {

    public int minimumLevels0(int[] possible) {
        int n = possible.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i > 0; i--) {
            suffix[i] = suffix[i + 1] - (possible[i] << 1) + 1;
        }
        for (int i = 0, sum = 0; i < n - 1; i++) {
            sum += (possible[i] << 1) - 1;
            if (sum + suffix[i + 1] > 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public int minimumLevels1(int[] possible) {
        int n = possible.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (possible[i] << 1) - 1;
        }
        for (int i = 1; i < n; i++) {
            if (preSum[i] << 1 > preSum[n]) {
                return i;
            }
        }
        return -1;
    }

    public int minimumLevels(int[] possible) {
        int n = possible.length, sum = 0;
        for (int p : possible) {
            sum += (p << 1) - 1;
        }
        sum = (sum << 1) - n;
        for (int i = 0, s = 0; i < n - 1; i++) {
            s += (possible[i] << 2) - 2;
            if (s > sum) {
                return i + 1;
            }
        }
        return -1;
    }
}
