package com.shuangpeng.Problem.p2701_2800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2740FindTheValueOfThePartition（找出分区值）
 * @date 2023/12/11 10:10 PM
 */
public class Problem2740FindTheValueOfThePartition {

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, n = nums.length;
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
}
