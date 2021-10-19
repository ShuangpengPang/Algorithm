package com.shuangpeng.competition.第261场周赛;

import java.util.Arrays;

public class Problem2028 {

    // 比赛时写法
    public int[] missingRolls0(int[] rolls, int mean, int n) {
        int m = rolls.length;
        long total = (m + n) * mean;
        long sum = 0;
        for (int roll : rolls) {
            sum += roll;
        }
        if (sum + n > total || sum + 6 * n < total) {
            return new int[0];
        }
        long remain = total - sum - n;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        for (int i = 0; i < n; ++i) {
            if (remain <= 0) {
                break;
            }
            if (remain >= 5) {
                ans[i] += 5;
                remain -= 5;
            } else {
                ans[i] += remain;
                remain = 0;
                break;
            }
        }
        return ans;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = mean * (m + n);
        for (int roll : rolls) {
            sum -= roll;
        }
        if (sum < n || sum > 6 * n) {
            return new int[0];
        }
        sum -= n;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int j = Math.min(5, sum);
            ans[i] = 1 + j;
            sum -= j;
        }
        return ans;
    }
}
