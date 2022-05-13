package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0995MinimumNumberOfKConsecutiveBitFlips（K连续位的最小翻转次数）
 * @Date 2022/5/9 4:48 PM
 * @Version 1.0
 */
public class Problem0995MinimumNumberOfKConsecutiveBitFlips {

    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k) {
                if (nums[i - k] > 1) {
                    --count;
                    nums[i - k] -= 2;
                }
            }
            if (((count + nums[i]) & 1) == 0) {
                if (i + k > n) {
                    return -1;
                }
                ++count;
                ++ans;
                nums[i] += 2;
            }
        }
        return ans;
    }
}
