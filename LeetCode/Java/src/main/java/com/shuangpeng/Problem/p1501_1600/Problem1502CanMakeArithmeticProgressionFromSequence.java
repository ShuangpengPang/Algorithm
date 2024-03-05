package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1502CanMakeArithmeticProgressionFromSequence（判断能否形成等差数列）
 * @date 2024/3/6 12:11 AM
 */
public class Problem1502CanMakeArithmeticProgressionFromSequence {

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != arr[i - 1] - arr[i - 2]) {
                return false;
            }
        }
        return true;
    }
}
