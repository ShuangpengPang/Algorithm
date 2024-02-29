package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1365HowManyNumbersAreSmallerThanTheCurrentNumber（有多少小于当前数字的数字）
 * @date 2024/2/29 11:29 PM
 */
public class Problem1365HowManyNumbersAreSmallerThanTheCurrentNumber {

    public int[] smallerNumbersThanCurrent(int[] nums) {
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
}
