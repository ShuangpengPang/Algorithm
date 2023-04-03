package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1053PreviousPermutationWithOneSwap（交换一次的先前排列）
 * @date 2023/4/3 10:14 AM
 */
public class Problem1053PreviousPermutationWithOneSwap {

    public int[] prevPermOpt10(int[] arr) {
        int n = arr.length, i = n -2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return arr;
        }
        int j = i + 1, k = j;
        while (j + 1 < n && arr[j + 1] < arr[i]) {
            j++;
            if (arr[j] > arr[k]) {
                k = j;
            }
        }
        arr[i] = arr[i] ^ arr[k];
        arr[k] = arr[i] ^ arr[k];
        arr[i] = arr[i] ^ arr[k];
        return arr;
    }

    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                int k = i + 1;
                for (int j = k; j < n && arr[j] < arr[i]; j++) {
                    if (arr[j] > arr[k]) {
                        k = j;
                    }
                }
                arr[i] = arr[i] ^ arr[k];
                arr[k] = arr[i] ^ arr[k];
                arr[i] = arr[i] ^ arr[k];
                break;
            }
        }
        return arr;
    }
}
