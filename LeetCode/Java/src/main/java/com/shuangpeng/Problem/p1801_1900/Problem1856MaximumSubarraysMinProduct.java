package com.shuangpeng.Problem.p1801_1900;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1856MaximumSubarraysMinProduct（子数组最小乘积的最大值）
 * @date 2023/10/11 5:11 PM
 */
public class Problem1856MaximumSubarraysMinProduct {

    public int maxSumMinProduct(int[] nums) {
        Deque<long[]> q = new ArrayDeque<>();
        q.offerLast(new long[]{0, 0});
        long ans = 0;
        for (int num : nums) {
            long sum = 0;
            while (num <= q.peekLast()[0]) {
                long[] p = q.pollLast();
                ans = Math.max(ans, p[0] * (p[1] + sum));
                sum += p[1];
            }
            q.offerLast(new long[]{num, sum + num});
        }
        long sum = 0;
        while (!q.isEmpty()) {
            long[] p = q.pollLast();
            sum += p[1];
            ans = Math.max(ans, p[0] * sum);
        }
        return (int) (ans % ((long) 1e9 + 7));
    }
}
