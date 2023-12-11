package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2712MinimumCostToMakeAllCharactersEqual（使所有字符相等的最小成本）
 * @date 2023/12/11 9:24 PM
 */
public class Problem2712MinimumCostToMakeAllCharactersEqual {

    public long minimumCost(String s) {
        int n = s.length();
        long ans = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }
}
