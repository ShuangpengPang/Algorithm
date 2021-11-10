package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.List;

public class Problem1048LongestStringChain {

    public int longestStrChain(String[] words) {
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
}
