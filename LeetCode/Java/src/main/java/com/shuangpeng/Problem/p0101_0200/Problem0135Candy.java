package com.shuangpeng.Problem.p0101_0200;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem0135Candy {

    public int candy0(int[] ratings) {
        int n = ratings.length;
        if (n == 1) {
            return 1;
        }
        int[] ans = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (ratings[a] != ratings[b]) {
                return ratings[a] - ratings[b];
            }
            return a - b;
        });
        for (int i = 0; i < n; ++i) {
            pq.offer(i);
        }
        while (!pq.isEmpty()) {
            int i = pq.poll();
            int candy = ratings[i];
            if (i == 0) {
                ans[i] = candy <= ratings[i + 1] ? 1 : ans[i + 1] + 1;
            } else if (i == n - 1) {
                ans[i] = candy <= ratings[i - 1] ? 1 : ans[i - 1] + 1;
            } else {
                if (candy <= Math.min(ratings[i - 1], ratings[i + 1])) {
                    ans[i] = 1;
                } else if (candy != Math.max(ratings[i - 1], ratings[i + 1]) || candy == ratings[i + 1]) {
                    ans[i] = Math.max(ans[i - 1], ans[i + 1]) + 1;
                } else {
                    ans[i] = ans[i + 1] + 1;
                }
            }
        }
        return Arrays.stream(ans).sum();
    }

    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int pre = Math.max(left[n - 1], 1);
        int ans = pre;
        for (int i = n - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                pre = Math.max(pre + 1, left[i]);
            } else {
                pre = left[i];
            }
            ans += pre;
        }
        return ans;
    }

    // [1,0,2]
    public int candy(int[] ratings) {
        int n = ratings.length;
        int ans = 1;
        int inc = 1, desc = 0, pre = 1;
        for (int i = 1; i < n; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                ++inc;
                desc = 0;
                ans += inc;
            } else if (ratings[i] == ratings[i - 1]) {
                inc = 1;
                desc = 0;
                ++ans;
            } else {
                ++desc;
                if (desc == 1) {
                    pre = inc;
                    inc = 1;
                }
                ans += desc;
                if (desc >= pre) {
                    ++ans;
                }
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0135Candy a = new Problem0135Candy();
//        a.candy(new int[]{1, 0, 2});
//    }
}
