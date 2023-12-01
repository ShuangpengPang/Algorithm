package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2567MinimumScoreByChangingTwoElements（修改两个元素的最小分数）
 * @date 2023/12/1 3:20 PM
 */
public class Problem2567MinimumScoreByChangingTwoElements {

    public int minimizeSum(int[] nums) {
        int[] arr1 = new int[4], arr2 = new int[4];
        Arrays.fill(arr1, Integer.MAX_VALUE);
        for (int num : nums) {
            addMin(arr1, num);
            addMax(arr2, num);
        }
        return Math.min(Math.min(arr2[0] - arr1[2], arr2[2] - arr1[0]), arr2[1] - arr1[1]);
    }

    private void addMin(int[] arr, int num) {
        int i = 0;
        while (i < 3 && num > arr[i]) {
            i++;
        }
        for (int j = 2; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        arr[i] = num;
    }

    private void addMax(int[] arr, int num) {
        int i = 0;
        while (i < 3 && num < arr[i]) {
            i++;
        }
        for (int j = 2; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        arr[i] = num;
    }
}
