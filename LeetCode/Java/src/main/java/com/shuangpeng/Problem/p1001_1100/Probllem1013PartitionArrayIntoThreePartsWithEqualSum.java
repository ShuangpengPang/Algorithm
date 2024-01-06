package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Probllem1013PartitionArrayIntoThreePartsWithEqualSum（将数组分成和相等的三部分）
 * @date 2024/1/6 6:11 PM
 */
public class Probllem1013PartitionArrayIntoThreePartsWithEqualSum {

    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int n = arr.length, part = sum / 3;
        int i = 0;
        for (int s = 0; i < n; i++) {
            s += arr[i];
            if (s == part) {
                break;
            }
        }
        int j = n - 1;
        for (int s = 0; j > i + 1; j--) {
            s += arr[j];
            if (s == part) {
                break;
            }
        }
        return i + 1 < j;
    }
}
