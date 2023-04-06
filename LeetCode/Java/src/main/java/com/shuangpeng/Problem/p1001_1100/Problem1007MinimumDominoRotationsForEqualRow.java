package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1007MinimumDominoRotationsForEqualRow（行相等的最少多米诺旋转）
 * @date 2023/4/6 4:10 PM
 */
public class Problem1007MinimumDominoRotationsForEqualRow {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = calculate(tops, bottoms, tops[0]);
        ans = Math.min(ans, calculate(bottoms, tops, tops[0]));
        if (tops[0] != bottoms[0]) {
            ans = Math.min(ans, calculate(tops, bottoms, bottoms[0]));
            ans = Math.min(ans, calculate(bottoms, tops, bottoms[0]));
        }
        return ans <= tops.length ? ans : -1;
    }

    private int calculate(int[] t, int[] b, int target) {
        int n = t.length, N = n + 1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (t[i] != target && b[i] != target) {
                return N;
            }
            if (t[i] != target) {
                ans++;
            }
        }
        return ans;
    }
}
