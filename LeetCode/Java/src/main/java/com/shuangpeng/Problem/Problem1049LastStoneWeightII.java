package com.shuangpeng.Problem;

public class Problem1049LastStoneWeightII {

    public int lastStoneWeightII0(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        boolean[] dp = new boolean[(sum >> 1) + 102];
        dp[0] = true;
        int min = sum;
        for (int i = 0; i < n; i++) {
            for (int j = dp.length - 1; j >= stones[i]; j--) {
                dp[j] = dp[j] || dp[j - stones[i]];
                if (dp[j]) {
                    min = Math.min(min, Math.abs((j << 1) - sum));
                }
            }
        }
        return min;
    }

    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        boolean[] dp = new boolean[(sum >> 1) + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = dp.length - 1; j >= stones[i]; j--) {
                dp[j] = dp[j] || dp[j - stones[i]];
            }
        }
        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[i]) {
                return sum - (i << 1);
            }
        }
        return 0;
    }

//    public static void main(String[] args) {
//        Problem1049LastStoneWeightII a = new Problem1049LastStoneWeightII();
//        a.lastStoneWeightII(new int[]{31,26,33,21,40});
//    }
}
