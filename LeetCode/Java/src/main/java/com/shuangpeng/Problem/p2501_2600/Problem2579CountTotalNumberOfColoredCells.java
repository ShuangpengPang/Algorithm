package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2579CountTotalNumberOfColoredCells（统计染色格子数）
 * @date 2023/12/3 10:41 PM
 */
public class Problem2579CountTotalNumberOfColoredCells {

    public long coloredCells0(int n) {
        long ans = 1;
        for (int i = 2; i <= n; i++) {
            ans += (i - 1) << 2;
        }
        return ans;
    }

    public long coloredCells(int n) {
        return 1 + ((long) n * (n - 1) << 1);
    }
}
