package com.shuangpeng.competition.第298场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2310SumOfNumbersWithUnitsDigitK（个位数字为K的整数之和）
 * @Date 2022/6/30 6:03 PM
 * @Version 1.0
 */
public class Problem2310SumOfNumbersWithUnitsDigitK {

    // 比赛时写法
    public int minimumNumbers0(int num, int k) {
        if (num == 0) {
            return 0;
        }
        int[] dp = new int[num + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 3);
        for (int i = 0; i <= num; i++) {
            if (i % 10 == k) {
                dp[i] = 1;
            } else {
                for (int j = 1; j <= i / 2; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + dp[i - j]);
                }
            }
        }
        return dp[num] == Integer.MAX_VALUE / 3 ? -1 : dp[num];
    }

    public int minimumNumbers(int num, int k) {
        if (num == 0) {
            return 0;
        }
        for (int i = 1; i <= 10 && i * k <= num; i++) {
            if ((num - i * k) % 10 == 0) {
                return i;
            }
        }
        return -1;
    }
}
