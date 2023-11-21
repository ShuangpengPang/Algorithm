package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2470NumberOfSubarraysWithLCMEqualToK（最小公倍数为K的子数组数目）
 * @date 2023/11/21 6:22 PM
 */
public class Problem2470NumberOfSubarraysWithLCMEqualToK {

    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int num = 1;
            for (int j = i; j < n && k % num == 0; j++) {
                num = num * nums[j] / gcd(num, nums[j]);
                if (num == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
