package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @Description: Problem1619MeanOfArrayAfterRemovingSomeElements（删除某些元素后的数组均值）
 * @Date 2022/9/14 10:33 AM
 * @Version 1.0
 */
public class Problem1619MeanOfArrayAfterRemovingSomeElements {

    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, m = n - n / 20;
        int sum = 0;
        for (int i = n / 20; i < m; i++) {
            sum += arr[i];
        }
        return (double) sum * 10 / (n * 9);
    }
}
