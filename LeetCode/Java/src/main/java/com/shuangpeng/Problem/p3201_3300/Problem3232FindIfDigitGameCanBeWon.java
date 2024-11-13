package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3232FindIfDigitGameCanBeWon（判断是否可以赢得数字游戏）
 * @date 2024/11/13 11:40 AM
 */
public class Problem3232FindIfDigitGameCanBeWon {

    public boolean canAliceWin(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num < 10 ? num : -num;
        }
        return sum != 0;
    }
}
