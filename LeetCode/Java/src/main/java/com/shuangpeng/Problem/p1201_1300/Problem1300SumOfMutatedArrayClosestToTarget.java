package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1300SumOfMutatedArrayClosestToTarget（转变数组后最接近目标值的数组和）
 * @date 2023/7/6 3:39 PM
 */
public class Problem1300SumOfMutatedArrayClosestToTarget {

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int sum = 0, n = arr.length;
        for (int num : arr) {
            sum += num;
        }
        if (sum <= target) {
            return arr[n - 1];
        }
        int i = n - 1;
        for (; i >= 0 && sum > target; i--) {
            int d = arr[i] - (i > 0 ? arr[i - 1] : 0);
            sum -= d * (n - i);
        }
        int p = i >= 0 ? arr[i] : 0;
        int add = (target - sum) / (n - i - 1), ans = p + add;
        sum += add * (n - i - 1);
        return target - sum <= sum + (n - i - 1) - target ? ans : ans + 1;
    }
}
