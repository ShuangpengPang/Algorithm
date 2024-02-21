package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP23IsMagic（魔术排列）
 * @date 2024/2/21 3:50 PM
 */
public class LCP23IsMagic {

    public boolean isMagic(int[] target) {
        int n = target.length;
        int[] arr = new int[n];
        for (int i = 0, j = 2; i < n; i++, j += 2) {
            if (j > n) {
                j = 1;
            }
            arr[i] = j;
        }
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != target[i]) {
                break;
            }
            k++;
        }
        if (k == 0) {
            return false;
        }
        return check(arr, target, k);
    }

    private boolean check(int[] arr1, int[] arr2, int k) {
        int n = arr1.length;
        for (int i = 0; i < n && i < k; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        if (k >= n) {
            return true;
        }
        int m = n - k;
        int[] arr3 = new int[m], arr4 = new int[m];
        for (int i = 0, j = k + 1; i < m; i++, j += 2) {
            if (j >= n) {
                j = k;
            }
            arr3[i] = arr1[j];
        }
        System.arraycopy(arr2, k, arr4, 0, m);
        return check(arr3, arr4, k);
    }
}
