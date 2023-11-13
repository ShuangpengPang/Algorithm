package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2207MaximizeNumberOfSubsequencesInAString（字符串中最多数目的子字符串）
 * @date 2023/11/13 4:45 PM
 */
public class Problem2207MaximizeNumberOfSubsequencesInAString {

    public long maximumSubsequenceCount(String text, String pattern) {
        int n = text.length(), cnt1 = 0, cnt2 = 0;
        long ans = 0;
        char c1 = pattern.charAt(0), c2 = pattern.charAt(1);
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (c == c2) {
                ans += cnt1;
                cnt2++;
            }
            if (c == c1) {
                cnt1++;
            }
        }
        return ans + Math.max(cnt1, cnt2);
    }
}
