package com.shuangpeng.Problem.p0101_0200;

public class Problem0122BestTimeToBuyAndSellStockII {

//    [7,1,5,3,6,4]

    public int maxProfit0(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, profit + prices[i] - prices[i - 1]);
            max = Math.max(max, profit);
        }
        return max;
    }

    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][3];
        dp[0][0] = 0; // 没有股票
        dp[0][1] = -prices[0]; // 持有股票
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[length - 1][0];
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int max = 0; // 没有股票
        int buy = -prices[0]; // 持有股票
        for (int i = 1; i < length; i++) {
            int temp = Math.max(max, buy + prices[i]);
            buy = Math.max(buy, max - prices[i]);
            max = temp;
        }
        return max;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int answer = 0;
        for (int i = 1; i < prices.length; i++) {
            answer += Math.max(0, prices[i] - prices[i - 1]);
        }
        return answer;
    }
}
