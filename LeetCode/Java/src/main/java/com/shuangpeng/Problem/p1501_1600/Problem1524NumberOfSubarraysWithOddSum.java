package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1524NumberOfSubarraysWithOddSum（和为奇数的子数组数目）
 * @date 2023/9/2 8:48 PM
 */
public class Problem1524NumberOfSubarraysWithOddSum {

    public int numOfSubarrays(int[] arr) {
        int n = arr.length, even = 1, odd = 0;
        long ans = 0, N = (long) 1e9 + 7;
        for (int i = 0, s = 0; i < n; i++) {
            s ^= arr[i] & 1;
            if (s == 0) {
                ans += odd;
                even++;
            } else {
                ans += even;
                odd++;
            }
        }
        return (int) (ans % N);
    }
}
