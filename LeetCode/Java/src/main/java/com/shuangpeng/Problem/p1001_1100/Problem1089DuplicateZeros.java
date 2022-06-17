package com.shuangpeng.Problem.p1001_1100;

/**
 * @Description: Problem1089DuplicateZeros（复写零）
 * @Date 2022/6/17 1:34 PM
 * @Version 1.0
 */
public class Problem1089DuplicateZeros {

    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        for (int i = 0, j = 0; i < n; ++i, ++j) {
            int num = arr[i] >= 10 ? (arr[i] / 10 - 1) : arr[i];
            if (num == 0) {
                if (j < n) {
                    arr[j] = (arr[j] + 1) * 10;
                }
                ++j;
            }
            if (j < n) {
                arr[j] = (arr[j] + 1) * 10 + num;
            }
            arr[i] %= 10;
        }
    }
}
