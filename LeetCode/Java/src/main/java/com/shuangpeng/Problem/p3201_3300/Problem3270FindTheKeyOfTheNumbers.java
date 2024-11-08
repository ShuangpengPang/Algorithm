package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3270FindTheKeyOfTheNumbers（求出数字答案）
 * @date 2024/11/7 7:22 PM
 */
public class Problem3270FindTheKeyOfTheNumbers {

    public int generateKey0(int num1, int num2, int num3) {
        int ans = 0;
        for (int i = 1; i <= 1000; i *= 10) {
            ans += i * (Math.min(num1 / i % 10, Math.min(num2 / i % 10, num3 / i % 10)));
        }
        return ans;
    }

    public int generateKey(int num1, int num2, int num3) {
        int ans = 0;
        for (int p = 1; p <= 1000 && num1 != 0 && num2 != 0 && num3 != 0; p *= 10) {
            ans += p * Math.min(num1 % 10, Math.min(num2 % 10, num3 % 10));
            num1 /= 10;
            num2 /= 10;
            num3 /= 10;
        }
        return ans;
    }
}
