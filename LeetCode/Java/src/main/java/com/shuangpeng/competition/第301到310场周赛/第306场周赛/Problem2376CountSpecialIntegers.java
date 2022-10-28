package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2376CountSpecialIntegers（统计特殊整数）
 * @Date 2022/10/28 3:14 PM
 * @Version 1.0
 */
public class Problem2376CountSpecialIntegers {

    public int countSpecialNumbers(int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int m = cs.length;
        int[] dp = new int[m];
        boolean[] used = new boolean[10];
        dp[0] = cs[0] - '0' - 1;
        used[cs[0] - '0'] = true;
        int cnt = 1;
        for (int i = 1; i < m; i++) {
            for (int j = i; j > 0; j--) {
                dp[j] = dp[j - 1] * (10 - j);
            }
            if (cnt == 1) {
                int num = cs[i] - '0';
                dp[i] += getCount(used, num);
                cnt = cnt == 0 ? cnt : (used[num] ? 0 : 1);
                used[num] = true;
            }
            dp[0] = 9;
        }
        return Arrays.stream(dp).sum() + cnt;
    }

    private int getCount(boolean[] used, int num) {
        int cnt = 0;
        for (int i = 0; i < num; i++) {
            if (!used[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
