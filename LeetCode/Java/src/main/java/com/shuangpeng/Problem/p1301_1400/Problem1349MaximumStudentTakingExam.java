package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1349MaximumStudentTakingExam（参加考试的最大学生数）
 * @Date 2022/8/6 3:23 PM
 * @Version 1.0
 */
public class Problem1349MaximumStudentTakingExam {

    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        List<Integer> states = new ArrayList<>();
        int N = 1 << n;
        for (int i = 0; i < N; i++) {
            int prev = -2;
            boolean valid = true;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    if (prev == j - 1) {
                        valid = false;
                        break;
                    }
                    prev = j;
                }
            }
            if (valid) {
                states.add(i);
            }
        }
        int len = states.size();
        int[][] dp = new int[2][len];
        for (int i = 0; i < len; i++) {
            int s = states.get(i);
            if (check(seats, s, 0)) {
                dp[0][i] = Integer.bitCount(s);
            }
        }
        for (int r = 1; r < m; r++) {
            int cur = r & 1, pre = cur ^ 1;
            for (int i = 0; i < len; i++) {
                int s = states.get(i);
                dp[cur][i] = 0;
                if (check(seats, s, r)) {
                    int max = 0;
                    int mask = getMask(n, s);
                    for (int j = 0; j < len; j++) {
                        int p = states.get(j);
                        if (p == (mask & p)) {
                            max = Math.max(max, dp[pre][j]);
                        }
                    }
                    dp[cur][i] = max + Integer.bitCount(s);
                }
            }
        }
        int cur = (m - 1) & 1;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, dp[cur][i]);
        }
        return ans;
    }

    private boolean check(char[][] seats, int s, int r) {
        int n = seats[0].length;
        for (int j = 0; j < n; j++) {
            if (((s >> j) & 1) == 1 && seats[r][j] == '#') {
                return false;
            }
        }
        return true;
    }

    private int getMask(int n, int s) {
        int m = (1 << n) - 1;
        for (int i = 0; i < n; i++) {
            if (((s >> i) & 1) == 1) {
                if (i - 1 >= 0) {
                    if (((m >> (i - 1)) & 1) == 1) {
                        m ^= 1 << (i - 1);
                    }
                }
                if (i + 1 < n) {
                    if (((m >> (i + 1)) & 1) == 1) {
                        m ^= 1 << (i + 1);
                    }
                }
            }
        }
        return m;
    }
}
