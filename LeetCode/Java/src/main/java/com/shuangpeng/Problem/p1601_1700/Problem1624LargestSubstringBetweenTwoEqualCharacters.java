package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @Description: Problem1624LargestSubstringBetweenTwoEqualCharacters（两个相同字符之间的最长子字符串）
 * @Date 2022/9/17 10:16 AM
 * @Version 1.0
 */
public class Problem1624LargestSubstringBetweenTwoEqualCharacters {

    public int maxLengthBetweenEqualCharacters(String s) {
        int n = s.length(), ans = -1;
        int[] first = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] != -1) {
                ans = Math.max(ans, i - first[c] - 1);
            } else {
                first[c] = i;
            }
        }
        return ans;
    }
}
