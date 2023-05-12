package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1330ReverseSubarrayToMaximizeArrayValue（翻转子数组得到的最大数组值）
 * @Date 2022/8/4 3:02 PM
 * @Version 1.0
 */
public class Problem1330ReverseSubarrayToMaximizeArrayValue {

    public int maxValueAfterReverse0(int[] nums) {
        int n = nums.length, sum = 0, add = 0;
        for (int i = 1; i < n - 1; i++) {
            add = Math.max(add, Math.abs(nums[i + 1] - nums[0]) - Math.abs(nums[i + 1] - nums[i]));
        }
        for (int i = n - 2; i > 0; i--) {
            add = Math.max(add, Math.abs(nums[i - 1] - nums[n - 1]) - Math.abs(nums[i - 1] - nums[i]));
        }
        int[][] arr = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(nums[i] - nums[i + 1]);
            if (nums[i] <= nums[i + 1]) {
                arr[i][0] = nums[i];
                arr[i][1] = nums[i + 1];
            } else {
                arr[i][0] = nums[i + 1];
                arr[i][1] = nums[i];
            }
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        int min = Integer.MAX_VALUE / 3;
        for (int[] p : arr) {
            add = Math.max(add, (p[0] - min) << 1);
            min = Math.min(min, p[1]);
        }
        return sum + add;
    }

    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length, sum = 0, add = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int a = Math.max(nums[i], nums[i + 1]);
            int b = Math.min(nums[i], nums[i + 1]);
            int d = a - b;
            sum += d;
            min = Math.min(min, a);
            max = Math.max(max, b);
            int num = Math.max(Math.abs(nums[i + 1] - nums[0]), Math.abs(nums[i] - nums[n - 1]));
            add = Math.max(add, num - d);
        }
        return sum + Math.max(add, (max - min) << 1);
    }
}
