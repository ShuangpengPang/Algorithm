package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem0901OnlineStockSpan（股票价格跨度）
 * @Date 2022/10/21 10:06 AM
 * @Version 1.0
 */
public class Problem0901OnlineStockSpan {
}

class StockSpanner {

    Deque<int[]> stack;
    int idx;

    public StockSpanner() {
        stack = new ArrayDeque<>();
        idx = 0;
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[1] <= price) {
            stack.pop();
        }
        int ans = stack.isEmpty() ? idx + 1 : idx - stack.peek()[0];
        stack.push(new int[]{idx++, price});
        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
