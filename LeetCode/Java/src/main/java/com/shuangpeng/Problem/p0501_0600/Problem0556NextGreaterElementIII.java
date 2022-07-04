package com.shuangpeng.Problem.p0501_0600;

import java.util.Arrays;

/**
 * @Description: Problem0556NextGreaterElementIII（下一个更大元素III）
 * @Date 2022/7/3 11:45 AM
 * @Version 1.0
 */
public class Problem0556NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        int size = 32 - Integer.numberOfLeadingZeros(n);
        Integer[] arr = new Integer[size];
        int temp = n;
        int cnt = 0;
        while (temp > 0) {
            arr[cnt++] = temp % 10;
            temp /= 10;
        }
        int idx = 1;
        while (idx < size && arr[idx] >= arr[idx - 1]) {
            idx++;
        }
        if (idx == size) {
            return -1;
        }
        int index = idx - 1;
        int num = arr[idx];
        for (int i = index - 1; i >= 0; i--) {
            int num1 = arr[i];
            if (num1 > num && num1 < arr[index]) {
                index = i;
            }
        }
        swap(arr, idx, index);
        Arrays.sort(arr, 0, idx, (a, b) -> b - a);
        int ans = 0, base = 1;
        for (int i = 0; i < size; i++) {
            ans += arr[i] * base;
            base *= 10;
        }
        return ans > 0 ? ans : -1;
    }

    private void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
