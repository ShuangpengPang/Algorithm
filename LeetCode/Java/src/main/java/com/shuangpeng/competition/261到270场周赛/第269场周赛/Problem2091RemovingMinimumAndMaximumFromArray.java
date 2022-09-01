package com.shuangpeng.competition.第269场周赛;

public class Problem2091RemovingMinimumAndMaximumFromArray {

    // 比赛时写法
    public int minimumDeletions0(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        int maxIndex = -1, minIndex = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
            if (nums[i] < minValue) {
                minValue = nums[i];
                minIndex = i;
            }
        }
        int index1 = Math.min(maxIndex, minIndex);
        int index2 = Math.max(maxIndex, minIndex);
        int ans = Math.min(index2 + 1, n - index1);
        ans = Math.min(ans, index1 + 1 + n - index2);
        return ans;
    }

    public int minimumDeletions(int[] nums) {
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        int minIndex = -1, maxIndex = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
            if (nums[i] < minValue) {
                minValue = nums[i];
                minIndex = i;
            }
        }
        int idx1 = Math.min(minIndex, maxIndex);
        int idx2 = Math.max(minIndex, maxIndex);
        int ans = Math.min(idx2 + 1, n - idx1);
        return Math.min(ans, idx1 + 1 + n - idx2);
    }
}
