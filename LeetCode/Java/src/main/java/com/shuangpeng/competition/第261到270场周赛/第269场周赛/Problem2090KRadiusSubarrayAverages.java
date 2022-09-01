package com.shuangpeng.competition.第261到270场周赛.第269场周赛;

import java.util.Arrays;

public class Problem2090KRadiusSubarrayAverages {

    // 比赛时写法
    public int[] getAverages0(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        long sum = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            sum += nums[j];
            if (j == 2 * k) {
                ans[j - k] = (int) (sum / (2 * k + 1));
            } else if (j - (2 * k + 1) >= 0) {
                sum -= nums[j - (2 * k + 1)];
                ans[j - k] = (int) (sum / (2 * k + 1));
            }
        }
        return ans;
    }

    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        long sum = 0;
        int count = (k << 1) + 1;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            if (i >= count) {
                sum -= nums[i - count];
            }
            if (i >= count - 1) {
                ans[i - k] = (int) (sum / count);
            }
        }
        return ans;
    }
}
