package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1262GreatestSumDivisibleByThree（可被三整除的最大和）
 * @date 2023/6/15 2:00 PM
 */
public class Problem1262GreatestSumDivisibleByThree {

    public int maxSumDivThree(int[] nums) {
        int[] arr1 = new int[4], arr2 = new int[4];
        arr1[1] = arr1[2] = arr2[1] = arr2[2] = Integer.MAX_VALUE >> 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if ((num % 3) == 1) {
                check(arr1, num);
            } else if ((num % 3) == 2) {
                check(arr2, num);
            }
        }
        arr1[2] += arr1[1];
        arr2[2] += arr2[1];
        int m = sum % 3;
        return sum - Math.min(arr1[m], arr2[3 - m]);
    }

    private void check(int[] arr, int num) {
        if (num <= arr[1]) {
            arr[2] = arr[1];
            arr[1] = num;
        } else if (num < arr[2]) {
            arr[2] = num;
        }
    }
}
