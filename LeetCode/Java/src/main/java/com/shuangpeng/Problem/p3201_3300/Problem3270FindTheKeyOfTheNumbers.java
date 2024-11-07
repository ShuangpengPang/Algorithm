package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3270FindTheKeyOfTheNumbers（求出数字答案）
 * @date 2024/11/7 7:22 PM
 */
public class Problem3270FindTheKeyOfTheNumbers {

    public int generateKey(int num1, int num2, int num3) {
        int ans = 0;
        for (int i = 1; i <= 1000; i *= 10) {
            ans += i * (Math.min(num1 / i % 10, Math.min(num2 / i % 10, num3 / i % 10)));
        }
        return ans;
    }
}
