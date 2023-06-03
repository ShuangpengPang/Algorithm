package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1073AddingTwoNegabinaryNumbers（负二进制数相加）
 * @date 2023/5/18 2:15 PM
 */
public class Problem1073AddingTwoNegabinaryNumbers {

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length, n = Math.max(n1, n2) + 2;
        int[] arr = new int[n];
        int carry = 0;
        for (int idx = n - 1, i = n1 - 1, j = n2 - 1; idx >= 0; idx--, i--, j--) {
            if (i >= 0) {
                carry += arr1[i];
            }
            if (j >= 0) {
                carry += arr2[j];
            }
            arr[idx] = carry & 1;
            carry = (carry - arr[idx]) / -2;
        }
        int i = 0;
        while (i < n - 1 && arr[i] == 0) {
            i++;
        }
        int m = n - i;
        int[] ans = new int[m];
        System.arraycopy(arr, i, ans, 0, m);
        return ans;
    }
}
