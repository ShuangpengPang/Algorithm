package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2576FindTheMaximumNumberOfMarkedIndices（找出最多标记下标）
 * @date 2023/12/3 10:31 PM
 */
public class Problem2576FindTheMaximumNumberOfMarkedIndices {

    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (int n = nums.length, j = (n + 1) >> 1; j < n; j++) {
            if (nums[i] << 1 <= nums[j]) {
                i++;
            }
        }
        return i << 1;
    }
}
