package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1144DecreaseElementsToMakeArrayZigzag（递减元素使数组呈锯齿状）
 * @date 2023/2/27 10:30 AM
 */
public class Problem1144DecreaseElementsToMakeArrayZigzag {

    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length, c1 = 0, c2 = 0;
        for (int i = 0; i < n; i++) {
            int m = Math.min(i - 1 < 0 ? Integer.MAX_VALUE : nums[i - 1], i + 1 >= n ? Integer.MAX_VALUE : nums[i + 1]);
            int c = Math.max(nums[i] - m + 1, 0);
            if ((i & 1) == 0) {
                c1 += c;
            } else {
                c2 += c;
            }
        }
        return Math.min(c1, c2);
    }
}
