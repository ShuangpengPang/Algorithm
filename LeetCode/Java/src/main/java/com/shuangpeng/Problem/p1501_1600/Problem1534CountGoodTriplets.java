package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1534CountGoodTriplets（统计好三元组）
 * @date 2024/3/17 10:10 PM
 */
public class Problem1534CountGoodTriplets {

    public int countGoodTriplets0(int[] arr, int a, int b, int c) {
        int n = arr.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    for (int k = j + 1; k < n; k++) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int countGoodTriplets1(int[] arr, int a, int b, int c) {
        int n = arr.length, maxValue = 0;
        for (int num : arr) {
            maxValue = Math.max(maxValue, num);
        }
        int[] cnt = new int[maxValue + 2];
        int ans = 0;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (Math.abs(arr[j] - arr[k]) <= b) {
                    int left = Math.max(0, Math.max(arr[j] - a, arr[k] - c));
                    int right = Math.min(maxValue, Math.min(arr[j] + a, arr[k] + c));
                    if (left <= right) {
                        ans += cnt[right + 1] - cnt[left];
                    }
                }
            }
            for (int i = arr[j] + 1; i <= maxValue + 1; i++) {
                cnt[i]++;
            }
        }
        return ans;
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, maxValue = 0;
        for (int num : arr) {
            maxValue = Math.max(maxValue, num);
        }
        int[] bit = new int[maxValue + 2];
        int ans = 0;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (Math.abs(arr[j] - arr[k]) <= b) {
                    int left = Math.max(0, Math.max(arr[j] - a, arr[k] - c));
                    int right = Math.min(maxValue, Math.min(arr[j] + a, arr[k] + c));
                    if (left <= right) {
                        ans += query(bit, right + 1) - query(bit, left);
                    }
                }
            }
            increment(bit, arr[j] + 1);
        }
        return ans;
    }

    private int query(int[] bit, int x) {
        int ans = 0;
        while (x != 0) {
            ans += bit[x];
            x ^= x & -x;
        }
        return ans;
    }

    private void increment(int[] bit, int x) {
        int n = bit.length;
        while (x < n) {
            bit[x]++;
            x += x & -x;
        }
    }
}
