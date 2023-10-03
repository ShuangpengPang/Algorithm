package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1849SplittingAStringIntoDescendingConsecutiveValues（将字符串拆分为递减的连续值）
 * @date 2023/9/28 4:34 PM
 */
public class Problem1849SplittingAStringIntoDescendingConsecutiveValues {

    public boolean splitString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long num = 0;
        for (int i = 0; i < n - 1; i++) {
            num = num * 10 + cs[i] - '0';
            if (num > 0 && dfs(cs, i + 1, num - 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(char[] cs, int start, long target) {
        int n = cs.length;
        if (start == n) {
            return true;
        }
        int i = start;
        long num = 0;
        while (i < n && num * 10 + cs[i] - '0' <= target) {
            num = num * 10 + cs[i++] - '0';
        }
        if (num != target) {
            return false;
        }
        return dfs(cs, i, target - 1);
    }
}
