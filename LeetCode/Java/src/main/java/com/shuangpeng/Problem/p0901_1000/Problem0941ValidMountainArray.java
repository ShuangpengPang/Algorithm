package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0941ValidMountainArray（有效的山脉数组）
 * @date 2024/1/8 11:36 PM
 */
public class Problem0941ValidMountainArray {

    public boolean validMountainArray(int[] arr) {
        int n = arr.length, i = 0;
        while (i + 1 < n && arr[i] < arr[i + 1]) {
            i++;
        }
        if (i == 0 || i == n - 1) {
            return false;
        }
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }
        return i == n - 1;
    }
}
