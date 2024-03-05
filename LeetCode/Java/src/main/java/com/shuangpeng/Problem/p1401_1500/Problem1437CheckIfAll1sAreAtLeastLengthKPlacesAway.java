package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1437CheckIfAll1sAreAtLeastLengthKPlacesAway（是否所有1都至少相隔K个元素）
 * @date 2024/3/5 10:36 AM
 */
public class Problem1437CheckIfAll1sAreAtLeastLengthKPlacesAway {

    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0, p = -k - 1; i < n; i++) {
            if (nums[i] == 1) {
                if (i - p <= k) {
                    return false;
                }
                p = i;
            }
        }
        return true;
    }
}
