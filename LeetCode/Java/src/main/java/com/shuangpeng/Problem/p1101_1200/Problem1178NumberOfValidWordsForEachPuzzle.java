package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1178NumberOfValidWordsForEachPuzzle（猜字谜）
 * @Date 2022/7/12 5:00 PM
 * @Version 1.0
 */
public class Problem1178NumberOfValidWordsForEachPuzzle {

    class Trie {
        Trie[] tries = new Trie[26];
        int count = 0;
    }

    Trie root;

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        root = new Trie();
        for (String w : words) {
            char[] chars = w.toCharArray();
            Arrays.sort(chars);
            int m = w.length();
            Trie trie = root;
            for (int i = 0; i < m; i++) {
                int j = chars[i] - 'a';
                if (i > 0 && chars[i] == chars[i - 1]) {
                    continue;
                }
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
            }
            trie.count++;
        }
        List<Integer> ans = new ArrayList<>(puzzles.length);
        for (String p : puzzles) {
            char[] chars = p.toCharArray();
            char ch = chars[0];
            Arrays.sort(chars);
            int n = chars.length;
            int idx = 0;
            while (idx < n && chars[idx] != ch) {
                idx++;
            }
            int M = 1 << n;
            int count = 0;
            for (int m = 1; m < M; m++) {
                if (((m >> idx) & 1) != 0) {
                    Trie trie = root;
                    boolean valid = true;
                    for (int i = 0; i < n; i++) {
                        if (((m >> i) & 1) == 1) {
                            int j = chars[i] - 'a';
                            if (trie.tries[j] == null) {
                                valid = false;
                                break;
                            }
                            trie = trie.tries[j];
                        }
                    }
                    if (valid) {
                        count += trie.count;
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}
