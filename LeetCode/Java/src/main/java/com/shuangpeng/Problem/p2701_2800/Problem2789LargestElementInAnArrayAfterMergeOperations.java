package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2789LargestElementInAnArrayAfterMergeOperations（合并后数组中的最大元素）
 * @date 2023/8/4 6:08 PM
 */
public class Problem2789LargestElementInAnArrayAfterMergeOperations {

    public long maxArrayValue(int[] nums) {
        long ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans = nums[i] > ans ? nums[i] : nums[i] + ans;
        }
        return ans;
    }
}
