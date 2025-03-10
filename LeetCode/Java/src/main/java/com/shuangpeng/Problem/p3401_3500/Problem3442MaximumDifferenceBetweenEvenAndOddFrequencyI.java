package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3442MaximumDifferenceBetweenEvenAndOddFrequencyI（奇偶频次间的最大差值I）
 * @date 2025/3/8 10:37
 */
public class Problem3442MaximumDifferenceBetweenEvenAndOddFrequencyI {

    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int n = s.length(), minEven = n, maxOdd = 0;
        for (int c : cnt) {
            if (c == 0) {
                continue;
            }
            if ((c & 1) == 0) {
                minEven = Math.min(minEven, c);
            } else {
                maxOdd = Math.max(maxOdd, c);
            }
        }
        return maxOdd - minEven;
    }
}
