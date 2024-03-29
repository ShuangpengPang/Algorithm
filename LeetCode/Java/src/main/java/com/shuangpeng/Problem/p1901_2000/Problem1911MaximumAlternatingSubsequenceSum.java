package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1911MaximumAlternatingSubsequenceSum（最大子序列交替和）
 * @date 2023/7/11 10:31 AM
 */
public class Problem1911MaximumAlternatingSubsequenceSum {

    public long maxAlternatingSum0(int[] nums) {
        long ans = 0;
        int prev = 0, i = 0, n = nums.length;
        while (i < n) {
            while (i + 1 < n && nums[i] <= nums[i + 1]) {
                i++;
            }
            ans += nums[i] - prev;
            while (i + 1 < n && nums[i] >= nums[i + 1]) {
                i++;
            }
            if (i < n) {
                prev = nums[i];
            }
            i++;
        }
        return ans;
    }

    public long maxAlternatingSum1(int[] nums) {
        int n = nums.length, i = 0, min = 0;
        long sum = 0;
        while (i < n) {
            while (i < n && (i == 0 || nums[i] >= nums[i - 1])) {
                i++;
            }
            sum += nums[i - 1] - min;
            while (i < n && nums[i] <= nums[i - 1]) {
                i++;
            }
            min = nums[i - 1];
        }
        return sum;
    }

    public long maxAlternatingSum2(int[] nums) {
        long sum = 0;
        for (int i = 0, p = 0; i < nums.length; i++) {
            if (nums[i] > p) {
                sum += nums[i] - p;
            }
            p = nums[i];
        }
        return sum;
    }

    public long maxAlternatingSum3(int[] nums) {
        int n = nums.length;
        long even = Integer.MIN_VALUE >> 1, odd = 0;
        for (int i = 0; i < n; i++) {
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, even - nums[i]);
        }
        return even;
    }


    public long maxAlternatingSum(int[] nums) {
        long ans = 0, min = 0, max = 0;
        for (int num : nums) {
            long tmpMax = num - min, tmpMin = num - max;
            if (tmpMax > max) {
                max = tmpMax;
                ans = tmpMax;
            }
            min = Math.min(min, tmpMin);
        }
        return ans;
    }
}
