package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1475FindPricesWithASpecialDiscountInAShop（商品折扣后的最终价格）
 * @Date 2022/9/1 10:02 AM
 * @Version 1.0
 */
public class Problem1475FindPricesWithASpecialDiscountInAShop {

    public int[] finalPrices(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int price = prices[i];
            while (!stack.isEmpty() && stack.peek() > price) {
                stack.pop();
            }
            ans[i] = price - (stack.isEmpty() ? 0 : stack.peek());
            stack.push(price);
        }
        return ans;
    }
}
