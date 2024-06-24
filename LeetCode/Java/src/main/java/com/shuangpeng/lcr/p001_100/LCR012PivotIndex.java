package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR012PivotIndex（寻找数组的中心下标）
 * @date 2024/5/6 12:16 PM
 */
public class LCR012PivotIndex {

    public int pivotIndex(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (left << 1 == sum - nums[i]) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
