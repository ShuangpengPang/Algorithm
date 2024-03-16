package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1539KthMissingPositiveNumber（第k个缺失的正整数）
 * @date 2024/3/16 5:41 PM
 */
public class Problem1539KthMissingPositiveNumber {

    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (mid + 1 + k > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return k + left;
    }
}
