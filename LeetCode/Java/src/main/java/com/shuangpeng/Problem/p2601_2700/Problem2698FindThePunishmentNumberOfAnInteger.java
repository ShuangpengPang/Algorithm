package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2698FindThePunishmentNumberOfAnInteger（求一个整数的惩罚数）
 * @date 2023/8/15 2:14 PM
 */
public class Problem2698FindThePunishmentNumberOfAnInteger {

    static int N = 1000;
    static int[] cnt = new int[N + 1];
    static {
        for (int i = 1; i <= N; i++) {
            cnt[i] = cnt[i - 1] + (dfs(Integer.toString(i * i), 0, i) ? i * i : 0);
        }
    }

    public int punishmentNumber(int n) {
        return cnt[n];
    }

    private static boolean dfs(String s, int start, int sum) {
        int n = s.length();
        if (start == n) {
            return sum == 0;
        }
        if (s.charAt(start) == '0') {
            return dfs(s, start + 1, sum);
        }
        int num = 0;
        for (int i = start; i < n && num <= sum; i++) {
            num = num * 10 + s.charAt(i) - '0';
            if (num <= sum && dfs(s, i + 1, sum - num)) {
                return true;
            }
        }
        return false;
    }
}
