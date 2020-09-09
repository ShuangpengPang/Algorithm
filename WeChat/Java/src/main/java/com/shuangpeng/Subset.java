package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/eQDHPiilPvW7Ro8568Helg
public class Subset {
    public static void main(String[] args) {
//        int[] data1 = {1, 5, 10, 16};
//        System.out.println("data1: " + checkSubsetByBackTrack(data1));
//        int[] data2 = {1, 2, 3, 5};
//        System.out.println("data2: " + checkSubsetByBackTrack(data2));
//        System.out.println("coin1: " + getMinCoinCount(new int[]{1, 2, 5}, 11));
//        System.out.println("coin2: " + getMinCoinCount(new int[]{2}, 3));
        System.out.println("coin1: " + coinConbineCount(new int[]{1, 2, 5}, 5));
        System.out.println("coin2: " + coinConbineCount(new int[]{2}, 3));
        System.out.println("coin3: " + coinConbineCount(new int[]{10}, 10));
    }

    public static boolean checkSubset(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < data.length; i++) {
            for (int j = target; j >= 0; j--) {
                if (j >= data[i]) {
                    dp[j] = dp[j - data[i]];
                }
            }
        }
        return dp[target];
    }

    public static boolean checkSubsetByBackTrack(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        return backtrack(data, 0, target);
    }

    public static boolean backtrack(int[] data, int start, int res) {
        if (res == 0) {
            return true;
        }
        if (start >= data.length) {
            return false;
        }
        return backtrack(data, start + 1, res) || backtrack(data, start + 1, res - data[start]);
    }

    public static int getMinCoinCount(int[] coins, int amount) {
        if (coins != null && coins.length == 0 && amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0 || amount <= 0) {
            return -1;
        }
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // dp[j], dp[j - coins[i]]
                if (dp[j - coins[i]] != -1) {
                    int temp = dp[j - coins[i]] + 1;
                    if (dp[j] != -1) {
                        temp = dp[j];
                    }
                    dp[j] = Math.min(temp, dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount];
    }

    public static int coinConbineCount(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
