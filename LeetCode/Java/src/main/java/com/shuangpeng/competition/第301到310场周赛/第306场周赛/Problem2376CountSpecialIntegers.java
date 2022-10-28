package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2376CountSpecialIntegers（统计特殊整数）
 * @Date 2022/10/28 3:14 PM
 * @Version 1.0
 */
public class Problem2376CountSpecialIntegers {

    public int countSpecialNumbers0(int n) {
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

    public int countSpecialNumbers(int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int[][] dp = new int[cs.length][1 << 10];
        Arrays.setAll(dp, i -> {
            Arrays.fill(dp[i], -1);
            return dp[i];
        });
        return dfs(cs, 0, 0, true, false, dp);
    }

    private int dfs(char[] cs, int idx, int mask, boolean isLimit, boolean isNum, int[][] memo) {
        if (idx == cs.length) {
            return isNum ? 1 : 0;
        }
        if (!isLimit && isNum && memo[idx][mask] >= 0) {
            return memo[idx][mask];
        }
        int ans = 0;
        if (!isNum) {
            ans = dfs(cs, idx + 1, mask, false, isNum, memo);
        }
        for (int i = isNum ? 0 : 1; i <= (isLimit ? cs[idx] - '0' : 9); i++) {
            if (((mask >> i) & 1) == 0) {
                ans += dfs(cs, idx + 1, mask | 1 << i, isLimit && i == (cs[idx] - '0'), true, memo);
            }
        }
        if (!isLimit && isNum) {
            memo[idx][mask] = ans;
        }
        return ans;
    }
}