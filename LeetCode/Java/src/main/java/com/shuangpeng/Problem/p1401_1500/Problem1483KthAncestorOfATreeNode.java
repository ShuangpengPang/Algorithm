package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

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

class Problem1483KthAncestorOfATreeNode0 {

    class TreeAncestor {

        List<Integer>[] g;
        int index;
        int[] levelMap, ids;
        List<TreeMap<Integer, Integer>> list;

        public TreeAncestor(int n, int[] parent) {
            index = 0;
            g = new List[n];
            list = new ArrayList<>();
            levelMap = new int[n];
            ids = new int[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int i = 0; i < n; i++) {
                if (parent[i] != -1) {
                    g[parent[i]].add(i);
                }
            }
            dfs(0, 0);
        }

        public int getKthAncestor(int node, int k) {
            int level = levelMap[node] - k;
            if (level < 0) {
                return -1;
            }
            return list.get(level).floorEntry(ids[node]).getValue();
        }

        private void dfs(int x, int level) {
            if (list.size() == level) {
                list.add(new TreeMap<>());
            }
            levelMap[x] = level;
            ids[x] = index;
            TreeMap<Integer, Integer> map = list.get(level);
            map.put(index++, x);
            for (int y : g[x]) {
                dfs(y, level + 1);
            }
        }
    }
}

class Problem1483KthAncestorOfATreeNode1 {

    class TreeAncestor {

        int[][] dp;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n);
            dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
                dp[i][0] = parent[i];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[j][i - 1] != -1) {
                        dp[j][i] = dp[dp[j][i - 1]][i - 1];
                    }
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            for (int i = 0; k >= 1 << i && node != -1; i++) {
                if ((k >> i & 1) == 1) {
                    node = dp[node][i];
                }
            }
            return node;
        }
    }
}

//class TreeAncestor {
//    private int[][] ST;
//
//    public TreeAncestor(int n, int[] parent) {
//        int maxIteration = 32 - Integer.numberOfLeadingZeros(n - 1);
//        ST = new int[n][maxIteration];
//        for (int i = 0; i < n; ++i){
//            ST[i][0] = parent[i];
//        }
//        for (int i = 0; i < n; ++i){
//            for (int j = 1; j < maxIteration; ++j){
//                ST[i][j] = ST[i][j - 1] == -1 ? -1 : ST[ST[i][j - 1]][j - 1];
//            }
//        }
//    }
//
//    public int getKthAncestor(int node, int k) {
//        if (node == -1 || k == 0){
//            return node;
//        }
//        int highestBit = Integer.highestOneBit(k);
//        int power = Integer.numberOfTrailingZeros(highestBit);
//        return getKthAncestor(ST[node][power], k & (~highestBit));
//    }
//}
