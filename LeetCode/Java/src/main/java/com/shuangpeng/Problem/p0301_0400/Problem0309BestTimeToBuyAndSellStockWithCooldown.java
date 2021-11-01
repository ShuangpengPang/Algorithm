package com.shuangpeng.Problem.p0301_0400;

public class Problem0309BestTimeToBuyAndSellStockWithCooldown {

    int max = 0;
    public int maxProfit0(int[] prices) {
        max = 0;
        backtrack(prices, 0, 0, 0, 0);
        return max;
    }

    // operation: (0: toBuy; 1: to Sell)
    public void backtrack(int[] prices, int index, int stock, int operation, int profit) {
        if (index >= prices.length) {
            max = Math.max(max, profit);
            return;
        }
        if (operation == 0) {
            int temp = stock;
            stock = prices[index];
            if (index + 1 < prices.length && prices[index + 1] <= prices[index]) {
                backtrack(prices, index + 1, temp, operation, profit);
                return;
            }
            backtrack(prices, index + 1, stock, 1, profit);
            backtrack(prices, index + 1, temp, operation, profit);
        } else {
            if (prices[index] - stock > 0) {
                backtrack(prices, index + 2, 0, 0, profit + prices[index] - stock);
            }
            backtrack(prices, index + 1, stock, operation, profit);
        }
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int first = -prices[0];
        int second = 0;
        int third = 0;
        for (int i = 1; i < length; i++) {
            int temp1 = Math.max(first, third - prices[i]);
            int temp2 = first + prices[i];
            int temp3 = Math.max(second, third);
            first = temp1;
            second = temp2;
            third = temp3;
        }
        return Math.max(second, third);
    }
}
