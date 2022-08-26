package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray（形成目标数组的子数组最少增加次数）
 * @Date 2022/8/26 4:26 PM
 * @Version 1.0
 */
public class Problem1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

    public int minNumberOperations0(int[] target) {
        int ans = 0, pre = 0;
        for (int num : target) {
            if (num > pre) {
                ans += num - pre;
            }
            pre = num;
        }
        return ans;
    }

    public int minNumberOperations(int[] target) {
        int ans = target[0], n = target.length;
        for (int i = 1; i < n; i++) {
            ans += Math.max(target[i] - target[i - 1], 0);
        }
        return ans;
    }
}
