package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0628MaximumProductOfThreeNumbers（三个数的最大乘积）
 * @date 2023/12/14 3:16 PM
 */
public class Problem0628MaximumProductOfThreeNumbers {

    public int maximumProduct0(int[] nums) {
        int n = nums.length;
        int one1 = Math.min(nums[0], Math.min(nums[1], nums[2]));
        int one2 = Math.max(nums[0], Math.max(nums[1], nums[2]));
        int two1 = Math.min(nums[0] * nums[1], Math.min(nums[0] * nums[2], nums[1] * nums[2]));
        int two2 = Math.max(nums[0] * nums[1], Math.max(nums[0] * nums[2], nums[1] * nums[2]));
        int ans = nums[0] * nums[1] * nums[2];
        for (int i = 3; i < n; i++) {
            ans = Math.max(ans, Math.max(two1 * nums[i], two2 * nums[i]));
            int p1 = one1 * nums[i], p2 = one2 * nums[i];
            two1 = Math.min(two1, Math.min(p1, p2));
            two2 = Math.max(two2, Math.max(p1, p2));
            one1 = Math.min(one1, nums[i]);
            one2 = Math.max(one2, nums[i]);
        }
        return ans;
    }

    public int maximumProduct(int[] nums) {
        int N = Integer.MAX_VALUE;
        int[] arr1 = new int[3], arr2 = new int[4];
        Arrays.fill(arr1, N);
        Arrays.fill(arr2, -N);
        for (int num : nums) {
            addMin(arr1, num);
            addMax(arr2, num);
        }
        return Math.max(arr2[0] * arr2[1] * arr2[2], arr1[0] * arr1[1] * arr2[0]);
    }

    private void addMin(int[] arr, int num) {
        int n = arr.length - 1, i = 0;
        while (i < n && num >= arr[i]) {
            i++;
        }
        for (int j = n - 1; j >= i; j--) {
            arr[j + 1] = arr[j];
        }
        arr[i] = num;
    }

    private void addMax(int[] arr, int num) {
        int n = arr.length - 1, i = 0;
        while (i < n && num <= arr[i]) {
            i++;
        }
        for (int j = n - 1; j >= i; j--) {
            arr[j + 1] = arr[j];
        }
        arr[i] = num;
    }
}
