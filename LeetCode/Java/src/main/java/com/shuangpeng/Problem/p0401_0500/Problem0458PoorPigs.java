package com.shuangpeng.Problem.p0401_0500;

public class Problem0458PoorPigs {

//    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
//        int k = minutesToTest / minutesToDie;
//        if (k >= buckets - 1) {
//            return Math.min(buckets - 1, 1);
//        }
//        int max = 10;
//        int[][] dp = new int[max + 1][k + 1];
//        for (int i = 0; i <= max; i++) {
//            dp[i][1] = (int) Math.pow(2, i);
//        }
//        for (int i = 1; i <= k; i++) {
//            dp[1][i] = i + 1;
//            dp[0][i] = 1;
//        }
//        for (int i = 2; i <= max; i++) {
//            for (int j = 2; j <= k; j++) {
//                dp[i][j] = dp[i - 1][j - 1] * i + dp[i - 2][j - 1] * i * (i - 1) / 2 + dp[i][j - 1];
//            }
//        }
//        for (int i = 1; i <= max; i++) {
//            if (dp[i][k] >= buckets) {
//                return i;
//            }
//        }
//        return 0;
//    }

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil(Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1));
    }

//    public static void main(String[] args) {
//        Problem0458PoorPigs a = new Problem0458PoorPigs();
//        a.poorPigs(1000, 12, 60);
//    }
}
