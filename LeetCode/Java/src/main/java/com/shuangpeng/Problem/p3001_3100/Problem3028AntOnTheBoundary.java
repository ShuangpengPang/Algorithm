package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3028AntOnTheBoundary（边界上的蚂蚁）
 * @date 2024/4/20 5:11 PM
 */
public class Problem3028AntOnTheBoundary {

    public int returnToBoundaryCount(int[] nums) {
        int ans = 0;
        for (int n = nums.length, i = 0, s = 0; i < n; i++) {
            if ((s += nums[i]) == 0) {
                ans++;
            }
        }
        return ans;
    }
}
