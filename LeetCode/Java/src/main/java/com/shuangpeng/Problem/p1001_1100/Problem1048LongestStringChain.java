package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 最长字符串链
 * @date 2023/4/27 10:36 AM
 **/
public class Problem1048LongestStringChain {

    public int longestStrChain0(String[] words) {
        final int N = 17;
        List<int[]>[] lists = new List[N];
        for (int i = 0; i < N; ++i) {
            lists[i] = new ArrayList<>();
        }
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            String word = words[i];
            lists[word.length()].add(new int[]{i, 1});
        }
        int maxLength = 1;
        for (int i = 2; i < N; ++i) {
            for (int[] pair1 : lists[i]) {
                String word1 = words[pair1[0]];
                for (int[] pair2 : lists[i - 1]) {
                    String word2 = words[pair2[0]];
                    if (check(word2, word1)) {
                        pair1[1] = Math.max(pair1[1], pair2[1] + 1);
                        maxLength = Math.max(maxLength, pair1[1]);
                    }
                }
            }
        }
        return maxLength;
    }

    private boolean check(String word1, String word2) {
        int i = 0, j = word1.length() - 1;
        int l = 0, r = word2.length() - 1;
        while (i <= j) {
            if (word1.charAt(i) == word2.charAt(l)) {
                ++i;
                ++l;
            } else if (word1.charAt(j) == word2.charAt(r)) {
                --j;
                --r;
            } else {
                return false;
            }
        }
        return true;
    }

    public int longestStrChain1(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        int ans = 0;
        Arrays.sort(words, Comparator.comparingInt(a -> a.length()));
        for (String w : words) {
            int m = w.length();
            int count = 0;
            for (int i = 0; i < m; i++) {
                String s = w.substring(0, i) + w.substring(i + 1);
                count = Math.max(count, dp.getOrDefault(s, 0));
            }
            count++;
            ans = Math.max(ans, count);
            dp.put(w, count);
        }
        return ans;
    }

    public int longestStrChain(String[] words) {
        List<String>[] lists = new List[17];
        Arrays.setAll(lists, i -> new ArrayList<>());
        for (String w : words) {
            lists[w.length()].add(w);
        }
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i <= 16; i++) {
            for (String w : lists[i]) {
                int m = w.length(), length = 0;
                for (int j = 0; j < m; j++) {
                    String s = w.substring(0, j) + w.substring(j + 1);
                    length = Math.max(length, map.getOrDefault(s, 0));
                }
                length++;
                ans = Math.max(ans, length);
                map.put(w, length);
            }
        }
        return ans;
    }
}
