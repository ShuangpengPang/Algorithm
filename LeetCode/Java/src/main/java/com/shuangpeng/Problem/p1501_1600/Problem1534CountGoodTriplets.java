package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1534CountGoodTriplets（统计好三元组）
 * @date 2024/3/17 10:10 PM
 */
public class Problem1534CountGoodTriplets {

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
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
}
