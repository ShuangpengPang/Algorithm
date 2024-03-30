package com.shuangpeng.Problem.p2101_2200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2160MinimumSumOfFourDigitNumberAfterSplittingDigits（拆分数位后四位数字的最小和）
 * @date 2024/3/30 3:52 PM
 */
public class Problem2160MinimumSumOfFourDigitNumberAfterSplittingDigits {

    public int minimumSum0(int num) {
        char[] cs = Integer.toString(num).toCharArray();
        Arrays.sort(cs);
        return (cs[0] + cs[1]) * 10 + cs[2] + cs[3] - 22 * '0';
    }

    public int minimumSum(int num) {
        int[] arr = new int[4];
        arr[0] = num % 10;
        arr[1] = num / 10 % 10;
        arr[2] = num / 100 % 10;
        arr[3] = num / 1000 % 10;
        Arrays.sort(arr);
        return (arr[0] + arr[1]) * 10 + arr[2] + arr[3];
    }
}
