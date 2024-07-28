package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3200MaximumHeightOfATriangle（三角形的最大高度）
 * @date 2024/7/28 7:40 PM
 */
public class Problem3200MaximumHeightOfATriangle {

    public int maxHeightOfTriangle(int red, int blue) {
        int left = 0, right = Math.max(red, blue);
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(new int[]{red, blue}, mid) || check(new int[]{blue, red}, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int[] arr, int level) {
        for (int i = 0; i < level; i++) {
            int j = i & 1;
            if (arr[j] < i + 1) {
                return false;
            }
            arr[j] -= i + 1;
        }
        return true;
    }
}
