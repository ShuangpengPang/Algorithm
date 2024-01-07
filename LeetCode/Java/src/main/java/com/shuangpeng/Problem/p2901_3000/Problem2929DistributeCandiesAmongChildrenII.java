package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2929DistributeCandiesAmongChildrenII（给小朋友们分糖果II）
 * @date 2024/1/7 5:52 PM
 */
public class Problem2929DistributeCandiesAmongChildrenII {

    public long distributeCandies0(int n, int limit) {
        long ans = 0;
        for (int i = Math.max(0, n - limit - limit); i <= limit && i <= n; i++) {
            ans += Math.min(limit, n - i) - Math.max(0, n - i - limit) + 1;
        }
        return ans;
    }

    public long distributeCandies(int n, int limit) {
        return c(n + 2) - 3 * c(n - limit + 1) + 3 * c(n - 2 * limit) - c(n - 3 * limit - 1);
    }

    private long c(long n) {
        return n > 1 ? n * (n - 1) >> 1 : 0;
    }
}
