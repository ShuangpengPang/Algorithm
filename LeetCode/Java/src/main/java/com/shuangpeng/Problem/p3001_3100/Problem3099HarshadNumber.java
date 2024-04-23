package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3099HarshadNumber（哈沙德数）
 * @date 2024/4/23 3:42 PM
 */
public class Problem3099HarshadNumber {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0, t = x;
        while (t != 0) {
            sum += t % 10;
            t /= 10;
        }
        return x % sum == 0 ? sum : -1;
    }
}
