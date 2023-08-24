package com.shuangpeng.Problem.p1401_1500;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1424DiagonalTraverseII（对角线遍历II）
 * @date 2023/8/24 5:36 PM
 */
public class Problem1424DiagonalTraverseII {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int m = nums.size(), n = 0, M = 0;
        for (List<Integer> list : nums) {
            n = Math.max(n, list.size());
            M += list.size();
        }
        int N = m + n - 1;
        int[] ans = new int[M];
        for (int i = 0, idx = 0; i < N; i++) {
            for (int r = Math.min(i, m - 1), c = i - r; r >= 0 && c < n; r--, c++) {
                if (c < nums.get(r).size()) {
                    ans[idx++] = nums.get(r).get(c);
                }
            }
        }
        return ans;
    }
}
