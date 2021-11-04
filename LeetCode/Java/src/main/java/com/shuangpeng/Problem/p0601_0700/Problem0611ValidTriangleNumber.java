package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;

public class Problem0611ValidTriangleNumber {

    public int triangleNumber0(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }
        return count;
    }

    public int triangleNumber1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < n - 1; j++) {
                int left = j + 1, right = n - 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (nums[mid] >= nums[i] + nums[j]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                count += left - j - 1;
            }
        }
        return count;
    }

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < n - 1; j++) {
                while (k < n && nums[k] < nums[i] + nums[j]) {
                    k++;
                }
                count += Math.max(0, k - j - 1);
            }
        }
        return count;
    }

    public int triangleNumber(int[] nums) {
        final int MAX = 1000;
        int[] sum = new int[MAX + 1];
        for (int num : nums) {
            sum[num]++;
        }
        sum[0] = 0;
        for (int i = 1; i <= MAX; i++) {
            sum[i] += sum[i - 1];
        }
        int count = 0;
        for (int i = 1; i <= MAX; i++) {
            int x = sum[i] - sum[i - 1];
            for (int j = i + 1; j < MAX; j++) {
                int y = sum[j] - sum[j - 1];
                count += x * y * (sum[Math.min(MAX, i + j - 1)] - sum[j]);
            }
            count += (sum[Math.min(MAX, i * 2 - 1)] - x) * (x * (x - 1) / 2);
            count += x * (x - 1) / 2 * (x - 2) / 3;
        }
        return count;
    }
}
