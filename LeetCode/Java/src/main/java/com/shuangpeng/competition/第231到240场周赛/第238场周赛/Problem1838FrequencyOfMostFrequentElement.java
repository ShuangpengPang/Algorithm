package com.shuangpeng.competition.第231到240场周赛.第238场周赛;

import java.util.Arrays;

public class Problem1838FrequencyOfMostFrequentElement {

    public int maxFrequency0(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 1;
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                int j = i - 1;
                int r = k;
                count = 1;
                while (j >= 0 && r >= nums[i] - nums[j]) {
                    r -= nums[i] - nums[j];
                    j--;
                    count++;
                }
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }

    public int maxFrequency1(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int left = 0;
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            total += (nums[i] - nums[i - 1]) * (i - left);
            while (total > k) {
                total -= nums[i] - nums[left];
                left++;
            }
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long preSum = 0, sum = 0;
        int ans = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            sum += nums[j];
            while (i <= j && preSum + (j - i + 1) * nums[j] > sum + k) {
                preSum += nums[i];
                ++i;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
