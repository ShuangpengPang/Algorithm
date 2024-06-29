package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR070SingleNonDuplicate（有序数组中的单一元素）
 * @date 2024/6/29 5:43 PM
 */
public class LCR070SingleNonDuplicate {

    public int singleNonDuplicate0(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public int singleNonDuplicate1(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i += 2) {
            if (nums[i - 1] != nums[i]) {
                return nums[i - 1];
            }
        }
        return nums[n - 1];
    }

    public int singleNonDuplicate2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] == nums[mid ^ 1]) {
                left = (mid & -2) + 2;
            } else {
                right = (mid & -2) - 1;
            }
        }
        return nums[left];
    }

    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
