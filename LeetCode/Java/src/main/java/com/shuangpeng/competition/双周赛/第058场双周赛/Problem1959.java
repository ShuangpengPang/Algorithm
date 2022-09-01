package com.shuangpeng.competition.双周赛.第058场双周赛;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem1959 {

    class Node {
        int left;
        int right;
        int maxValue;
        Node leftNode;
        Node rightNode;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
            this.maxValue = Integer.MIN_VALUE;
        }

        public int getMid() {
            return left + ((right - left) >> 1);
        }
    }

    private void updateNode(Node root, int i, int value) {
        if (root.left == root.right) {
            root.maxValue = Math.max(root.maxValue, value);
            return;
        }
        int mid = root.getMid();
        if (root.leftNode == null) {
            root.leftNode = new Node(root.left, mid);
        }
        if (root.rightNode == null) {
            root.rightNode = new Node(mid + 1, root.right);
        }
        if (i <= mid) {
            updateNode(root.leftNode, i, value);
        } else {
            updateNode(root.rightNode, i, value);
        }
        root.maxValue = Math.max(root.leftNode.maxValue, root.rightNode.maxValue);
    }

    private int getMaxValue(Node root, int s, int e) {
        if (s <= root.left && root.right <= e) {
            return root.maxValue;
        }
        int mid = root.getMid();
        if (e <= mid) {
            return getMaxValue(root.leftNode, s, e);
        }
        if (s > mid) {
            return getMaxValue(root.rightNode, s, e);
        }
        int leftValue = getMaxValue(root.leftNode, s, mid);
        int rightValue = getMaxValue(root.rightNode, mid + 1, e);
        return Math.max(leftValue, rightValue);
    }

    public int minSpaceWastedKResizing0(int[] nums, int k) {
        int n = nums.length;
        Node root = new Node(0, n - 1);
        int[] sum = new int[n];
        updateNode(root, 0, nums[0]);
        sum[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            updateNode(root, i, nums[i]);
            sum[i] = sum[i - 1] + nums[i];
        }
        return minWastedSpace(nums, nums.length - 1, k + 1, root, sum, new HashMap<>());
    }

    private int minWastedSpace(int[] nums, int end, int k, Node root, int[] sum, Map<Integer, Integer> memo) {
        if (k >= end + 1) {
            return 0;
        }
        int key = k * nums.length + end;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (k == 1) {
            int maxValue = getMaxValue(root, 0, end);
            int waste = maxValue * (end + 1) - sum[end];
            memo.put(key, waste);
            return waste;
        }
        int minValue = Integer.MAX_VALUE;
        for (int i = end; i >= k - 1; --i) {
            int maxValue = getMaxValue(root, i, end);
            int waste = maxValue * (end - i + 1) - (sum[end] - sum[i - 1]);
            minValue = Math.min(minValue, waste + minWastedSpace(nums, i - 1, k - 1, root, sum, memo));
        }
        memo.put(key, minValue);
        return minValue;
    }

    public int minSpaceWastedKResizing1(int[] nums, int k) {
        k++;
        int n = nums.length;
        int[] maxArray = new int[n];
        int[] sumArray = new int[n];
        maxArray[0] = nums[0];
        sumArray[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            maxArray[i] = Math.max(maxArray[i - 1], nums[i]);
            sumArray[i] = sumArray[i - 1] + nums[i];
        }
        int[][] dp = new int[n][k + 1];
        for (int i = 1; i < n; ++i) {
            dp[i][1] = maxArray[i] * (i + 1) - sumArray[i];
            for (int j = 2; j <= k; ++j) {
                if (j >= i + 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                    int maxValue = nums[i];
                    for (int m = i - 1; m >= j - 1; --m) {
                        maxValue = Math.max(maxValue, nums[m]);
                        dp[i][j] = Math.min(dp[i][j], dp[m - 1][j - 1] + maxValue * (i - m + 1) - sumArray[i] + sumArray[m - 1]);
                    }
                }
            }
        }
        return dp[n - 1][k];
    }

    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            int maxValue = nums[i];
            int sum = nums[i];
            for (int j = i + 1; j < n; ++j) {
                maxValue = Math.max(maxValue, nums[j]);
                sum += nums[j];
                g[i][j] = maxValue * (j - i + 1) - sum;
            }
        }
        int[][] dp = new int[n][k + 2];
        final int INF = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], INF);
            dp[i][1] = g[0][i];
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 2; j <= k + 1; ++j) {
                for (int m = 1; m <= i; ++m) {
                    dp[i][j] = Math.min(dp[i][j], dp[m - 1][j - 1] + g[m][i]);
                }
            }
        }
        return dp[n - 1][k + 1];
    }
}
