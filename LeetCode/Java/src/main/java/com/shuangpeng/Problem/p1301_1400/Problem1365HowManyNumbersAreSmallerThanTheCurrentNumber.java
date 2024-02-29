package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1365HowManyNumbersAreSmallerThanTheCurrentNumber（有多少小于当前数字的数字）
 * @date 2024/2/29 11:29 PM
 */
public class Problem1365HowManyNumbersAreSmallerThanTheCurrentNumber {

    public int[] smallerNumbersThanCurrent0(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        int[] preSum = new int[101];
        for (int i = 0, s = 0; i <= 100; i++) {
            preSum[i] = s;
            s += cnt[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = preSum[nums[i]];
        }
        return ans;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(e -> nums[e]));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (nums[ids[mid]] < nums[i]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans[i] = left;
        }
        return ans;
    }
}
