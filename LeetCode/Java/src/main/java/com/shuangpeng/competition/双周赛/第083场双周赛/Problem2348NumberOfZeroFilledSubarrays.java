package com.shuangpeng.competition.双周赛.第083场双周赛;

/**
 * @Description: Problem2348NumberOfZeroFilledSubarrays（全0子数组的数目）
 * @Date 2022/8/16 10:47 PM
 * @Version 1.0
 */
public class Problem2348NumberOfZeroFilledSubarrays {

    // 比赛时写法
    public long zeroFilledSubarray0(int[] nums) {
        int n = nums.length;
        long ans = 0L;
        for (int i = 0, l = -1; i < n; i++) {
            if (nums[i] == 0) {
                if (l == -1) {
                    l = i;
                }
                ans += i - l + 1;
            } else {
                l = -1;
            }
        }
        return ans;
    }

    public long zeroFilledSubarray(int[] nums) {
        long ans = 0L;
        int cnt = 0;
        for (int num : nums) {
            if (num != 0) {
                cnt = 0;
            } else {
                ans += ++cnt;
            }
        }
        return ans;
    }
}
