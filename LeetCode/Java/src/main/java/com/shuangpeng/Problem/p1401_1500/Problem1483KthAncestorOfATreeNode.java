package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1483KthAncestorOfATreeNode（树节点的第K个祖先）
 * @Date 2022/8/22 2:52 PM
 * @Version 1.0
 */
public class Problem1483KthAncestorOfATreeNode {

    class TreeAncestor {

        int m, n, maxHeight;
        int[][] dp;

        public TreeAncestor(int n, int[] parent) {
            this.n = n;
            int[] height = new int[n];
            for (int i = 0; i < n; i++) {
                maxHeight = Math.max(maxHeight, getHeight(parent, height, i));
            }
            m = 1;
            int h = maxHeight;
            while (h > 1) {
                h >>= 1;
                m++;
            }
            dp = new int[n][m];
            Arrays.fill(dp[0], -1);
            for (int i = 1; i < n; i++) {
                Arrays.fill(dp[i], -2);
            }
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dfs(parent, i, j, dp);
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            if (k > maxHeight) {
                return -1;
            }
            int j = 0;
            while (k > 0 && node != -1) {
                if ((k & 1) == 1) {
                    node = dp[node][j];
                }
                j++;
                k >>= 1;
            }
            return node;
        }

        private int getHeight(int[] parent, int[] height, int x) {
            if (x == 0 || height[x] != 0) {
                return height[x];
            }
            height[x] = getHeight(parent, height, parent[x]) + 1;
            return height[x];
        }

        private int dfs(int[] parent, int i, int j, int[][] dp) {
            if (i == 0 || dp[i][j] != -2) {
                return dp[i][j];
            }
            if (j == 0) {
                dp[i][j] = parent[i];
                return parent[i];
            }
            int node = dfs(parent, i, j - 1, dp);
            if (node == -1) {
                dp[i][j] = -1;
                return -1;
            }
            dp[i][j] = dfs(parent, node, j - 1, dp);
            return dp[i][j];
        }
    }

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
}

class TreeAncestor {
    private int[][] ST;

    public TreeAncestor(int n, int[] parent) {
        int maxIteration = 32 - Integer.numberOfLeadingZeros(n - 1);
        ST = new int[n][maxIteration];
        for (int i = 0; i < n; ++i){
            ST[i][0] = parent[i];
        }
        for (int i = 0; i < n; ++i){
            for (int j = 1; j < maxIteration; ++j){
                ST[i][j] = ST[i][j - 1] == -1 ? -1 : ST[ST[i][j - 1]][j - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        if (node == -1 || k == 0){
            return node;
        }
        int highestBit = Integer.highestOneBit(k);
        int power = Integer.numberOfTrailingZeros(highestBit);
        return getKthAncestor(ST[node][power], k & (~highestBit));
    }
}

