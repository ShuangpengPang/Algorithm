package com.shuangpeng.Problem.p0101_0200;

import java.util.Arrays;

public class Problem0164MaximumGap {

    public int maximumGap0(int[] nums) {
        int maxValue = Arrays.stream(nums).max().getAsInt();
        int n = nums.length;
        int[] buf = new int[n];
        int exp = 1;
        while (maxValue >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; ++i) {
                ++cnt[nums[i] / exp % 10];
            }
            for (int i = 1; i < 10; ++i) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; --i) {
                int digit = nums[i] / exp % 10;
                buf[--cnt[digit]] = nums[i];
            }
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }

    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }
        int d = Math.max(1, (maxValue - minValue) / (n - 1));
        int bucketSize = (maxValue - minValue) / d + 1;
        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            bucket[i][0] = Integer.MAX_VALUE;
            bucket[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < n; ++i) {
            int idx = (nums[i] - minValue) / d;
            bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
            bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
        }
        int ans = 0, preMax = Integer.MAX_VALUE;
        for (int i = 0; i < bucketSize; ++i) {
            if (bucket[i][0] != Integer.MAX_VALUE) {
                ans = Math.max(ans, bucket[i][0] - preMax);
                preMax = bucket[i][1];
            }
        }
        return ans;
    }
}
