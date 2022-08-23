package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1494ParallelCoursesII（并行课程II）
 * @Date 2022/8/23 4:25 PM
 * @Version 1.0
 */
public class Problem1494ParallelCoursesII {

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int M = 1 << n;
        int[] pre = new int[n];
        for (int[] r : relations) {
            pre[r[1] - 1] |= 1 << (r[0] - 1);
        }
        int[] dp = new int[M];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int learned = 0; learned < M; learned++) {
            int canStudy = 0;
            for (int i = 0; i < n; i++) {
                if ((pre[i] & learned) == pre[i]) {
                    canStudy |= 1 << i;
                }
            }
            canStudy &= ~learned;
            for (int subset = canStudy; subset > 0; subset = (subset - 1) & canStudy) {
                if (Integer.bitCount(subset) > k) {
                    continue;
                }
                dp[learned | subset] = Math.min(dp[learned | subset], dp[learned] + 1);
            }
        }
        return dp[M - 1];
    }
}

//class Solution {
//    public:
//    int minNumberOfSemesters(int n, vector<vector<int>>& dependencies, int k) {
//        vector<int> prereq(n);
//        for (const auto& dep: dependencies) {
//            prereq[dep[1] - 1] |= (1 << (dep[0] - 1));
//        }
//
//        vector<int> set_prereq(1 << n), valid(1 << n);
//        for (int mask = 0; mask < (1 << n); ++mask) {
//            if (__builtin_popcount(mask) <= k) {
//                for (int i = 0; i < n; ++i) {
//                    if (mask & (1 << i)) {
//                        set_prereq[mask] |= prereq[i];
//                    }
//                }
//                valid[mask] = ((set_prereq[mask] & mask) == 0);
//            }
//        }
//
//        vector<int> dp(1 << n, INT_MAX / 2);
//        dp[0] = 0;
//        for (int mask = 0; mask < (1 << n); ++mask) {
//            for (int subset = mask; subset; subset = (subset - 1) & mask) {
//                if (valid[subset] && ((mask & set_prereq[subset]) == set_prereq[subset])) {
//                    dp[mask] = min(dp[mask], dp[mask ^ subset] + 1);
//                }
//            }
//        }
//        return dp[(1 << n) - 1];
//    }
//};


