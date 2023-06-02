package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2559CountVowelStringsInRanges（统计范围内的元音字符串数）
 * @date 2023/6/2 10:27 AM
 */
public class Problem2559CountVowelStringsInRanges {

    public int[] vowelStrings0(String[] words, int[][] queries) {
        boolean[] vowel = new boolean[26];
        char[] arr = {'a', 'e', 'i', 'o', 'u'};
        for (char c : arr) {
            vowel[c - 'a'] = true;
        }
        int n = words.length, m = queries.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int c1 = words[i].charAt(0), c2 = words[i].charAt(words[i].length() - 1);
            preSum[i + 1] = preSum[i] + (vowel[c1 - 'a'] && vowel[c2 - 'a'] ? 1 : 0);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int j = queries[i][0], k = queries[i][1];
            ans[i] = preSum[k + 1] - preSum[j];
        }
        return ans;
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length, m = queries.length;
        boolean[] vowel = new boolean[26];
        char[] arr = {'a', 'e', 'i', 'o', 'u'};
        for (char c : arr) {
            vowel[c - 'a'] = true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String w = words[i];
            int c1 = w.charAt(0) - 'a', c2 = w.charAt(w.length() - 1) - 'a';
            if (vowel[c1] && vowel[c2]) {
                list.add(i);
            }
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int left = queries[i][0], right = queries[i][1];
            ans[i] = binarySearch(list, right) - binarySearch(list, left - 1);
        }
        return ans;
    }

    private int binarySearch(List<Integer> list, int index) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (list.get(mid) <= index) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }
}
