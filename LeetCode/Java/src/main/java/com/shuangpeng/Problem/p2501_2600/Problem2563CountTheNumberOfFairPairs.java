package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2563CountTheNumberOfFairPairs（统计公平数对的数目）
 * @date 2023/12/1 9:27 AM
 */
public class Problem2563CountTheNumberOfFairPairs {

    public long countFairPairs0(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = 0, s = n - 1, e = n - 1; i < e; i++) {
            while (i < e && nums[i] + nums[e] > upper) {
                e--;
            }
            while (i < s && nums[i] + nums[s] >= lower) {
                s--;
            }
            ans += e - Math.max(s, i);
        }
        return ans;
    }

    public long countFairPairs1(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = n - 1, s = 0, e = 0; s < i; i--) {
            while (e < i && nums[i] + nums[e] <= upper) {
                e++;
            }
            while (s < i && nums[i] + nums[s] < lower) {
                s++;
            }
            ans += Math.min(i, e) - s;
        }
        return ans;
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return getCount(nums, upper) - getCount(nums, lower - 1);
    }

    private long getCount(int[] nums, int upper) {
        long ans = 0;
        for (int i = 0, j = nums.length - 1; i < j; i++) {
            while (i < j && nums[i] + nums[j] > upper) {
                j--;
            }
            ans += j - i;
        }
        return ans;
    }
}
