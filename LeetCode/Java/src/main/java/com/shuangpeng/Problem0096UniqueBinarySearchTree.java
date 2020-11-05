package com.shuangpeng;

import java.util.HashMap;
import java.util.Map;

public class Problem0096UniqueBinarySearchTree {

//    public static void main(String[] args) {
//        Problem0096UniqueBinarySearchTree a = new Problem0096UniqueBinarySearchTree();
//        int i = a.numTrees(2);
//        int j = i;
//    }

    public int numTrees(int n) {
        return recursive(n);
    }

    // 动态规划
    public int recursive(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                sum += dp[j - 1] * dp[i - j];
            }
            dp[i] = sum;
        }
        return dp[n];
    }
}
