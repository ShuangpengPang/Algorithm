package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1641CountSortedVowelStrings（统计字典序元音字符串的数目）
 * @date 2023/3/29 1:51 PM
 */
public class Problem1641CountSortedVowelStrings {

    public int countVowelStrings0(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 4; j >= 0; j--) {
                for (int k = 0; k < j; k++) {
                    dp[j] += dp[k];
                }
            }
        }
        return Arrays.stream(dp).sum();
    }

    public int countVowelStrings1(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < 5; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return Arrays.stream(dp).sum();
    }

    public int countVowelStrings(int n) {
        int ans = 1;
        for (int i = 1; i <= 4; i++) {
            ans = ans * (n + i) / i;
        }
        return ans;
    }
}
