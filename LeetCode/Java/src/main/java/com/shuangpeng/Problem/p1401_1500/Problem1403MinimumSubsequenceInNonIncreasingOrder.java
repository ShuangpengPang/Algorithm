package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1403MinimumSubsequenceInNonIncreasingOrder（非递增顺序的子序列）
 * @Date 2022/8/4 10:46 AM
 * @Version 1.0
 */
public class Problem1403MinimumSubsequenceInNonIncreasingOrder {

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int total = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            total += nums[i];
        }
        int sum = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0 && sum <= total; i--) {
            sum += nums[i];
            total -= nums[i];
            ans.add(nums[i]);
        }
        return ans;
    }
}
