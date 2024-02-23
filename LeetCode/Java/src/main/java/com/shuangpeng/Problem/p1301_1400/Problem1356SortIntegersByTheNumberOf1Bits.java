package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1356SortIntegersByTheNumberOf1Bits（根据数字二进制下1的数目排序）
 * @date 2024/2/23 3:36 PM
 */
public class Problem1356SortIntegersByTheNumberOf1Bits {

    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> {
            int c1 = bitCount(arr[a]), c2 = bitCount(arr[b]);
            return c1 != c2 ? c1 - c2 : arr[a] - arr[b];
        });
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = arr[ids[i]];
        }
        return ans;
    }

    private int bitCount(int i) {
        i = i - (i >>> 1 & 0x55555555);
        i = (i & 0x33333333) + (i >>> 2 & 0x33333333);
        i = (i + (i >>> 4)) & 0xf0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
}

class Problem1356SortIntegersByTheNumberOf1Bits0 {

    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> {
            int c1 = bitCount(arr[a]), c2 = bitCount(arr[b]);
            return c1 != c2 ? c1 - c2 : arr[a] - arr[b];
        });
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = arr[ids[i]];
        }
        return ans;
    }

    private int bitCount(int i) {
        int cnt = 0;
        while (i != 0) {
            i ^= i & -i;
            cnt++;
        }
        return cnt;
    }
}

class Problem1356SortIntegersByTheNumberOf1Bits01 {

    private static int N = 10000;
    private static int[] bit = new int[N + 1];
    static {
        for (int i = 1; i <= N; i++) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
    }

    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> arr[i]);
        Arrays.sort(ids, (a, b) -> bit[a] != bit[b] ? bit[a] - bit[b] : a - b);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = ids[i];
        }
        return ans;
    }
}
