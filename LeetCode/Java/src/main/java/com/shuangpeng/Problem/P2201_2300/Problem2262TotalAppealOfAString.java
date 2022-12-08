package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2262TotalAppealOfAString（字符串的总引力）
 * @date 2022/12/8 12:02 PM
 */
public class Problem2262TotalAppealOfAString {

    public long appealSum(String s) {
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        long ans = 0L, prev = 0L;
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            prev += i - last[j];
            ans += prev;
            last[j] = i;
        }
        return ans;
    }
}
