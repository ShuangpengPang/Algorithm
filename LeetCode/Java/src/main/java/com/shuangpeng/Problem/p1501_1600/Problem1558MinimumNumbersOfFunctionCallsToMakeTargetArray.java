package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1558MinimumNumbersOfFunctionCallsToMakeTargetArray（得到目标数组的最少函数调用次数）
 * @date 2023/9/5 5:40 PM
 */
public class Problem1558MinimumNumbersOfFunctionCallsToMakeTargetArray {

    public int minOperations0(int[] nums) {
        int ans = 0, maxBit = 1;
        for (int num : nums) {
            ans += Integer.bitCount(num);
            maxBit = Math.max(maxBit, 32 - Integer.numberOfLeadingZeros(num));
        }
        return ans + maxBit - 1;
    }

    public int minOperations(int[] nums) {
        int ans = 0, maxBit = 1;
        for (int num : nums) {
            int cnt = 0;
            while (num != 0) {
                ans += num & 1;
                num >>= 1;
                cnt++;
            }
            maxBit = Math.max(maxBit, cnt);
        }
        return ans + maxBit - 1;
    }
}
