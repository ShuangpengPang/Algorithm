package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem0901OnlineStockSpan（股票价格跨度）
 * @Date 2022/10/21 10:06 AM
 * @Version 1.0
 */
public class Problem0901OnlineStockSpan {

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
}

class Problem0901OnlineStockSpan0 {

    class StockSpanner {

        private Deque<int[]> q;
        private int day;

        public StockSpanner() {
            q = new ArrayDeque<>();
            q.offerLast(new int[]{Integer.MAX_VALUE, 0});
            day = 0;
        }

        public int next(int price) {
            while (q.peekLast()[0] <= price) {
                q.pollLast();
            }
            day++;
            int span = day - q.peekLast()[1];
            q.offerLast(new int[]{price, day});
            return span;
        }
    }
}

class Problem0901OnlineStockSpan1 {
    static class StockSpanner {

        static int N = (int) 1e4, idx = 0, size = 100;
        static int[] nums = new int[N], region = new int[N / size + 1];

        public StockSpanner() {
            int id = getRegion(idx);
            for (int i = 0; i <= id; i++) {
                region[i] = 0;
            }
            idx = 0;
        }

        public int next(int price) {
            nums[idx] = price;
            int id = getRegion(idx);
            region[id] = Math.max(region[id], price);
            idx++;
            return query(price);
        }

        private int getRegion(int x) {
            return x / size;
        }

        private int query(int price) {
            int id = getRegion(idx - 1), left = id * size, right = idx;
            int ans = 0;
            while (id >= 0 && region[id] <= price) {
                ans += right - left;
                id--;
                right = left;
                left -= size;
            }
            if (id >= 0) {
                for (int i = right - 1; i >= left && nums[i] <= price; i--) {
                    ans++;
                }
            }
            return ans;
        }
    }
}
