package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2235AddTwoIntegers（两整数相加）
 * @date 2023/8/19 3:20 PM
 */
public class Problem2235AddTwoIntegers {

    public int sum(int num1, int num2) {
        while (num2 != 0) {
            int tmp = num1;
            num1 ^= num2;
            num2 = (num2 & tmp) << 1;
        }
        return num1;
    }
}
