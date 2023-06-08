package com.shuangpeng.Problem.p1101_1200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1170CompareStringsByFrequencyOfTheSmallestCharacter（比较字符串最小字母出现频次）
 * @date 2023/6/8 12:11 PM
 */
public class Problem1170CompareStringsByFrequencyOfTheSmallestCharacter {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = getFreq(words[i]);
        }
        Arrays.sort(cnt);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int freq = getFreq(queries[i]);
            ans[i] = binarySearch(cnt, freq);
        }
        return ans;
    }

    private int binarySearch(int[] cnt, int num) {
        int left = 0, right = cnt.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (cnt[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return cnt.length - left;
    }

    private int getFreq(String w) {
        int[] cnt = new int[26];
        int min = 26, f = 0;
        for (char c : w.toCharArray()) {
            int j = c - 'a';
            if (j <= min) {
                f = ++cnt[j];
                min = Math.min(min, j);
            }
        }
        return f;
    }
}
