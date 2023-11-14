package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2216MinimumDeletionsToMakeArrayBeautiful（美化数组的最少删除数）
 * @date 2023/11/14 1:53 PM
 */
public class Problem2216MinimumDeletionsToMakeArrayBeautiful {

    // 证明：https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful/solutions/1372292/by-newhar-st2d/
    public int minDeletion0(int[] nums) {
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

    public int minDeletion(int[] nums) {
        int n = nums.length, ans = 0;
        int i = 0, j = 1;
        while (j < n) {
            if (nums[i] != nums[j]) {
                ans += j - i - 1;
                i = j + 1;
            }
            j++;
        }
        return ans + j - i;
    }
}
