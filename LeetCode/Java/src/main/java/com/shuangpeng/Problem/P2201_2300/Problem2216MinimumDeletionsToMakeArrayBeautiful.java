package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2216MinimumDeletionsToMakeArrayBeautiful（美化数组的最少删除数）
 * @date 2023/11/14 1:53 PM
 */
public class Problem2216MinimumDeletionsToMakeArrayBeautiful {

    // 证明：https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/solutions/1372292/by-newhar-st2d/
    public int minDeletion(int[] nums) {
        int n = nums.length, ans = 0;
        boolean odd = false;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (odd) {
                if (prev != nums[i]) {
                    odd = false;
                } else {
                    ans++;
                }
            } else {
                prev = nums[i];
                odd = true;
            }
        }
        return odd ? ans + 1 : ans;
    }
}
