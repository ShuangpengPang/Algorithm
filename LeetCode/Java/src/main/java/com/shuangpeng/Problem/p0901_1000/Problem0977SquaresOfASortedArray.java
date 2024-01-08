package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0977SquaresOfASortedArray（有序数组的平方）
 * @date 2024/1/8 9:49 PM
 */
public class Problem0977SquaresOfASortedArray {

    public int[] sortedSquares0(int[] nums) {
        int n = nums.length, left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int[] ans = new int[n];
        int i = left - 1, j = left, index = 0;
        while (i >= 0 && j < n) {
            int s1 = nums[i] * nums[i], s2 = nums[j] * nums[j];
            if (s1 <= s2) {
                ans[index++] = s1;
                i--;
            } else {
                ans[index++] = s2;
                j++;
            }
        }
        while (i >= 0) {
            ans[index++] = nums[i] * nums[i];
            i--;
        }
        while (j < n) {
            ans[index++] = nums[j] * nums[j];
            j++;
        }
        return ans;
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = n - 1, left = 0, right = n - 1; i >= 0; i--) {
            int s1 = nums[left] * nums[left], s2 = nums[right] * nums[right];
            if (s1 >= s2) {
                ans[i] = s1;
                left++;
            } else {
                ans[i] = s2;
                right--;
            }
        }
        return ans;
    }
}
