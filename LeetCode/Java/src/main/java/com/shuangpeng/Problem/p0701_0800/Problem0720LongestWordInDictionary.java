package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0720LongestWordInDictionary
 * @Date 2022/3/21 4:32 PM
 * @Version 1.0
 */
public class Problem0720LongestWordInDictionary {

    class Trie {
        Trie[] tries;

        Trie() {
            tries = new Trie[26];
        }
    }

    public String longestWord0(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(a -> a.length()));
        Trie root = new Trie();
        String ans = "";
        for (String w : words) {
            int n = w.length();
            Trie trie = root;
            for (int i = 0; i < n; ++i) {
                int idx = w.charAt(i) - 'a';
                if (trie.tries[idx] != null) {
                    trie = trie.tries[idx];
                } else {
                    if (i == n - 1) {
                        trie.tries[idx] = new Trie();
                        if (ans.length() < n || (ans.length() == n && ans.compareTo(w) > 0)) {
                            ans = w;
                        }
                    }
                    break;
                }
            }
        }
        return ans;
    }

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return b.compareTo(a);
        });
        Set<String> set = new HashSet<>();
        String ans = "";
        set.add(ans);
        for (String w : words) {
            if (set.contains(w.substring(0, w.length() - 1))) {
                set.add(w);
                ans = w;
            }
        }
        return ans;
    }
}
