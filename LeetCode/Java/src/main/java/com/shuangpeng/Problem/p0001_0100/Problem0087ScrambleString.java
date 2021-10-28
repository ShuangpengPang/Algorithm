package com.shuangpeng.Problem.p0001_0100;

import java.util.HashMap;
import java.util.Map;

public class Problem0087ScrambleString {

    // 记忆化搜索存储状态的数组
    // -1 表示 false，1 表示 true，0 表示未计算
    int[][][] memo;
    String s1, s2;

    public boolean isScramble0(String s1, String s2) {
        int length = s1.length();
        this.memo = new int[length][length][length + 1];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, length);
    }

    // 第一个字符串从 i1 开始，第二个字符串从 i2 开始，子串的长度为 length，是否和谐
    public boolean dfs(int i1, int i2, int length) {
        if (memo[i1][i2][length] != 0) {
            return memo[i1][i2][length] == 1;
        }

        // 判断两个子串是否相等
        if (s1.substring(i1, i1 + length).equals(s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = 1;
            return true;
        }

        // 判断是否存在字符 c 在两个子串中出现的次数不同
        if (!checkIfSimilar(i1, i2, length)) {
            memo[i1][i2][length] = -1;
            return false;
        }

        // 枚举分割位置
        for (int i = 1; i < length; ++i) {
            // 不交换的情况
            if (dfs(i1, i2, i) && dfs(i1 + i, i2 + i, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
            // 交换的情况
            if (dfs(i1, i2 + length - i, i) && dfs(i1 + i, i2, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
        }

        memo[i1][i2][length] = -1;
        return false;
    }

    public boolean checkIfSimilar(int i1, int i2, int length) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = i1; i < i1 + length; ++i) {
            char c = s1.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (int i = i2; i < i2 + length; ++i) {
            char c = s2.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) - 1);
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            int value = entry.getValue();
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isScramble1(String s1, String s2) {
        int n = s1.length();
        int[][][] memo = new int[n][n][n + 1];
        return dfs(s1, s2, memo, 0, 0, n);
    }

    private boolean dfs(String s1, String s2, int[][][] memo, int i1, int i2, int length) {
        if (memo[i1][i2][length] != 0) {
            return memo[i1][i2][length] == 1;
        }
        if (isEqual(s1, s2, i1, i2, length)) {
            memo[i1][i2][length] = 1;
            return true;
        }
        if (!isSimilar(s1, s2, i1, i2, length)) {
            memo[i1][i2][length] = -1;
            return false;
        }
        for (int l = 1; l < length; l++) {
            if (dfs(s1, s2, memo, i1, i2, l) && dfs(s1, s2, memo, i1 + l, i2 + l, length - l)) {
                memo[i1][i2][length] = 1;
                return true;
            }
            if (dfs(s1, s2, memo, i1, i2 + length - l, l) && dfs(s1, s2, memo, i1 + l, i2, length - l)) {
                memo[i1][i2][length] = 1;
                return true;
            }
        }
        memo[i1][i2][length] = -1;
        return false;
    }

    private boolean isEqual(String s1, String s2, int i1, int i2, int length) {
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i1 + i) != s2.charAt(i2 + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSimilar(String s1, String s2, int i1, int i2, int length) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = s1.charAt(i1 + i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            char c = s2.charAt(i2 + i);
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                for (int j = 0; j <= n - length; j++) {
                    for (int l = 1; l < length; l++) {
                        if (dp[i][j][l] && dp[i + l][j + l][length - l]) {
                            dp[i][j][length] = true;
                            break;
                        }
                        if (dp[i][j + length - l][l] && dp[i + l][j][length - l]) {
                            dp[i][j][length] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
