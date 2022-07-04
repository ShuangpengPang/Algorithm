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

class Problem0556NextGreaterElementIII0 {

    public int nextGreaterElement(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        int m = chars.length;
        int idx = m - 2;
        while (idx >= 0 && chars[idx] >= chars[idx + 1]) {
            idx--;
        }
        if (idx < 0) {
            return -1;
        }
        int index = m - 1;
        while (chars[index] <= chars[idx]) {
            index--;
        }
        swap(chars, idx, index);
        for (int i = idx + 1, j = m - 1; i < j; i++, j--) {
            swap(chars, i, j);
        }
        long ans = Long.parseLong(new String(chars));
        return (int) (ans > Integer.MAX_VALUE ? -1 : ans);
    }

    private void swap(char[] chars, int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }
}

class Problem0556NextGreaterElementIII1 {

    public int nextGreaterElement(int n) {
        int x = n, cnt1 = 1;
        while (x / 10 % 10 >= x % 10) {
            x /= 10;
            cnt1++;
        }
        x /= 10;
        if (x == 0) {
            return -1;
        }
        int num = x % 10;
        int t = n, cnt2 = 0;
        while (t % 10 <= num) {
            t /= 10;
            cnt2++;
        }
        x += t % 10 - num;
        for (int i = 0; i < cnt1; i++) {
            int digit = i != cnt2 ? n % 10 : num;
            if (x > Integer.MAX_VALUE / 10 || (x == Integer.MAX_VALUE / 10 && digit > 7)) {
                return -1;
            }
            x = x * 10 + digit;
            n /= 10;
        }
        return x;
    }
}
