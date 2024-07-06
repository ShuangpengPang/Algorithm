package com.shuangpeng.lcr.p001_100;

import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR100MinimumTotal
 * @date 2024/7/6 10:30 AM
 */
public class LCR100MinimumTotal {

    public int minimumTotal0(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = i; j > 0; j--) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + list.get(j);
            }
            dp[0] += list.get(0);
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> cur = triangle.get(i), next = triangle.get(i + 1);
            for (int j = 0; j <= i; j++) {
                cur.set(j, cur.get(j) + Math.min(next.get(j), next.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
