package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1497CheckIfArrayPairsAreDivisibleByK（检查数组对是否可以被k整除）
 * @date 2023/8/29 5:17 PM
 */
public class Problem1497CheckIfArrayPairsAreDivisibleByK {

    public boolean canArrange(int[] arr, int k) {
        int[] cnt = new int[k];
        for (int num : arr) {
            cnt[(num % k + k) % k]++;
        }
        for (int i = 1; i <= k >> 1; i++) {
            if (cnt[i] != cnt[k - i]) {
                return false;
            }
        }
        return (k & 1) == 1 || (cnt[k >> 1] & 1) == 0;
    }
}
