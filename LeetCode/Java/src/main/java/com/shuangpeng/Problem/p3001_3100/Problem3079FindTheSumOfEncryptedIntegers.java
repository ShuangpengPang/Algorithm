package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3079FindTheSumOfEncryptedIntegers（求出加密整数的和）
 * @date 2024/4/22 3:56 PM
 */
public class Problem3079FindTheSumOfEncryptedIntegers {

    public int sumOfEncryptedInt(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += encrypt(num);
        }
        return sum;
    }

    private int encrypt(int x) {
        int maxDigit = 0, p = 0;
        while (x != 0) {
            maxDigit = Math.max(maxDigit, x % 10);
            x /= 10;
            p = p * 10 + 1;
        }
        return maxDigit * p;
    }
}
