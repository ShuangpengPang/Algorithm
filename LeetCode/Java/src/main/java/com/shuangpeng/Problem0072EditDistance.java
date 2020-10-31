package com.shuangpeng;

import java.util.HashMap;
import java.util.Map;

public class Problem0072EditDistance {

//    public static void main(String[] args) {
//        Problem0072EditDistance a = new Problem0072EditDistance();
//////        int i = a.minDistance("algorithm", "altruistic");
//        int i = a.minDistance("distance", "springbok");
////        int j = 1;
//    }

    // 动态规划
    public int editDistance0(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 == 0 || length2 == 0) {
            return Math.max(length1, length2);
        }

        // ab c
        // def g

        // replace: 1 + dp[i - 1][j - 1]
        // insert: 1 + dp[i][j - 1]
        // delete: 1 + dp[i - 1][j]
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length2; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[length1][length2];
    }

    // 动态规划（一维数组）
    public int editDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 * length2 == 0) {
            return length1 + length2;
        }
        int[] dp = new int[length2 + 1];
        for (int i = 0; i < length2 + 1; i++) {
            dp[i] = i;
        }
        for (int i = 1; i < length1 + 1; i++) {
            int previous = i - 1;
            dp[0] = i;
            for (int j = 1; j < length2 + 1; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = previous;
                } else {
                    dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), previous);
                }
                previous = temp;
            }
        }
        return dp[length2];
    }

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
//        int maxStep = Math.max(word1.length(), word2.length());
//        return backtrack(word1, word2, maxStep);
        return editDistance(word1, word2);
    }

    Map<String, Integer> memo = new HashMap<>();

    /**
     *
     * @param word1
     * @param word2
     * @param leftStep
     * @return 如果能在（maxStep - step）步内返回就返回最小值，否则返回Integer.MAX_VALUE表示没最优解
     */
    public int backtrack(String word1, String word2, int leftStep) {
        int maxLen = Math.max(word1.length(), word2.length());
        int minLen = Math.min(word1.length(), word2.length());
        if (leftStep < 0 || maxLen - minLen > leftStep) {
            return Integer.MAX_VALUE;
        }
        if (word1.equals(word2)) {
            return 0;
        }
        String key = word1 + '-' + word2;
        if (word1.equals("") || word2.equals("")) {
            if (maxLen > leftStep) {
                return Integer.MAX_VALUE;
            }
            return maxLen;
        }
        if (memo.containsKey(key)) {
            if (memo.get(key) > leftStep) {
                return Integer.MAX_VALUE;
            }
            return memo.get(key);
        }
        if (word1.charAt(0) == word2.charAt(0)) {
            int result = backtrack(word1.substring(1), word2.substring(1), leftStep);
            if (result != Integer.MAX_VALUE) {
                memo.put(key, result);
            }
            return result;
        }
        int delete = backtrack(word1.substring(1), word2, leftStep - 1);
        int insert = backtrack(word1, word2.substring(1), leftStep - 1);
        int replace = backtrack(word1.substring(1), word2.substring(1), leftStep - 1);
        int result = Math.min(Math.min(delete, insert), replace);


        // ajkst
        // bksaa
        // aX bY
        // X < Y
        // 1 + p(X, bY)  delete
        // 1 + p(X, Y) replace
        // 1 + p(aX, Y ) insert

        if (result != Integer.MAX_VALUE) {
            result++;
        }
        if (result != Integer.MAX_VALUE) {
            memo.put(key, result);
        }
        return result;
    }
}
