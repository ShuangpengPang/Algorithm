package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3361ShiftDistanceBetweenTwoStrings（两个字符串的切换距离）
 * @date 2025/4/1 16:17
 */
public class Problem3361ShiftDistanceBetweenTwoStrings {

    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        long[][] next = new long[26][26];
        next[25][0] = nextCost[25];
        for (int i = 1; i < 25; i++) {
            next[25][i] = next[25][i - 1] + nextCost[i - 1];
        }
        for (int i = 24; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (i != j) {
                    next[i][j] = nextCost[i] + next[i + 1][j];
                }
            }
        }
        long[][] prev = new long[26][26];
        prev[0][25] = previousCost[0];
        for (int i = 24; i > 0; i--) {
            prev[0][i] = prev[0][i + 1] + previousCost[i + 1];
        }
        for (int i = 1; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i != j) {
                    prev[i][j] = prev[i - 1][j] + previousCost[i];
                }
            }
        }
        long[][] dis = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dis[i][j] = Math.min(next[i][j], prev[i][j]);
            }
        }
        int n = s.length();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dis[s.charAt(i) - 'a'][t.charAt(i) - 'a'];
        }
        return ans;
    }
}
