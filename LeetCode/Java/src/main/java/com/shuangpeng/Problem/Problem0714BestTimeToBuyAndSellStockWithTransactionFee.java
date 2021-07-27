package com.shuangpeng.Problem;

public class Problem0714BestTimeToBuyAndSellStockWithTransactionFee {

    public int maxProfit0(int[] prices, int fee) {
        int n = prices.length;
        int buy = -prices[0], sell = 0;
        for (int i = 1; i < n; i++) {
            int temp = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = temp;
        }
        return sell;
    }

    public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buy) {
                maxProfit += prices[i] - buy;
                buy = prices[i];
            } else if (buy > prices[i] + fee) {
                buy = prices[i] + fee;
            }
        }
        return maxProfit;
    }
}
