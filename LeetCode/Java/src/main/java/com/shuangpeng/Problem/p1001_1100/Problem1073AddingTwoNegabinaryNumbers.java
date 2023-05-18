package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1073AddingTwoNegabinaryNumbers（负二进制数相加）
 * @date 2023/5/18 2:15 PM
 */
public class Problem1073AddingTwoNegabinaryNumbers {

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length, n = Math.max(n1, n2);
        int[] arr = new int[n];
        int carry = 0;
        for (int idx = n - 1, i = n1 - 1, j = n2 - 1; idx >= 0; idx--, i--, j--) {
            if (i >= 0) {
                carry += arr1[i];
            }
            if (j >= 0) {
                carry += arr2[j];
            }
            arr[idx] = carry % (-2) == 0 ? 0 : 1;
            carry = (carry - arr[idx]) / (-2);
        }
        int[] ans = null;
        if (carry == 0) {
            int i = 0;
            while (i < n && arr[i] == 0) {
                i++;
            }
            if (i == n) {
                return new int[1];
            }
            int m = n - i;
            ans = new int[m];
            System.arraycopy(arr, i, ans, 0, m);
        } else if (carry == 1) {
            ans = new int[n + 1];
            ans[0] = 1;
            System.arraycopy(arr, 0, ans, 1, n);
        } else if (carry == -1) {
            ans = new int[n + 2];
            ans[0] = ans[1] = 1;
            System.arraycopy(arr, 0, ans, 2, n);
        }
        return ans;
    }
}
