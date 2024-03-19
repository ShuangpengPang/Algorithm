package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1869LongerContiguousSegmentsOfOnesThanZeros（哪种连续子字符串更长）
 * @date 2024/3/19 5:48 PM
 */
public class Problem1869LongerContiguousSegmentsOfOnesThanZeros {

    public boolean checkZeroOnes0(String s) {
        int[] cnt = new int[2];
        int n = s.length();
        for (int i = 0, count = 0; i < n; i++) {
            count++;
            char c = s.charAt(i);
            if (i == n - 1 || s.charAt(i + 1) != c) {
                cnt[c - '0'] = Math.max(cnt[c - '0'], count);
                count = 0;
            }
        }
        return cnt[1] > cnt[0];
    }

    public boolean checkZeroOnes(String s) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[2];
        int n = cs.length, i = 0;
        while (i < n) {
            int start = i;
            char c = cs[start];
            while (i < n && cs[i] == c) {
                i++;
            }
            cnt[c - '0'] = Math.max(cnt[c - '0'], i - start);
        }
        return cnt[1] > cnt[0];
    }
}
