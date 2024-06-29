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

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i += 2) {
            if (nums[i - 1] != nums[i]) {
                return nums[i - 1];
            }
        }
        return nums[n - 1];
    }
}
