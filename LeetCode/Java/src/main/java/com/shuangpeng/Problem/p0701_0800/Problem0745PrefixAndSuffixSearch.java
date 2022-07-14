package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem0745PrefixAndSuffixSearch（前缀和后缀搜索）
 * @Date 2022/7/14 10:28 AM
 * @Version 1.0
 */
public class Problem0745PrefixAndSuffixSearch {

    class WordFilter {

        Map<String, List<Integer>> prefix, suffix;

        public WordFilter(String[] words) {
            int n = words.length;
            prefix = new HashMap<>(7 * n);
            suffix = new HashMap<>(7 * n);
            for (int i = 0; i < n; i++) {
                String w = words[i];
                int m = w.length();
                for (int j = 0; j < m; j++) {
                    prefix.computeIfAbsent(w.substring(0, j + 1), k -> new ArrayList<>()).add(i);
                    suffix.computeIfAbsent(w.substring(j), k -> new ArrayList<>()).add(i);
                }
            }
        }

        public int f(String pref, String suff) {
            List<Integer> list1 = prefix.get(pref), list2 = suffix.get(suff);
            if (list1 == null || list2 == null) {
                return -1;
            }
            int i = list1.size() - 1, j = list2.size() - 1;
            while (i >= 0 && j >= 0) {
                int idx1 = list1.get(i), idx2 = list2.get(j);
                if (idx1 < idx2) {
                    j--;
                } else if (idx1 > idx2) {
                    i--;
                } else {
                    return idx1;
                }
            }
            return -1;
        }
    }

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
}

class WordFilter {

    class Trie {
        Trie[] tries = new Trie[26];
        List<Integer> indices = new ArrayList<>();
    }

    private void add(Trie trie, String s, int idx) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            if (trie.tries[j] == null) {
                trie.tries[j] = new Trie();
            }
            trie = trie.tries[j];
            trie.indices.add(idx);
        }
    }

    private List<Integer> query(Trie trie, String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            trie = trie.tries[j];
            if (trie == null) {
                return null;
            }
        }
        return trie.indices;
    }

    Trie prefix, suffix;

    public WordFilter(String[] words) {
        prefix = new Trie();
        suffix = new Trie();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            add(prefix, words[i], i);
            add(suffix, new StringBuilder(words[i]).reverse().toString(), i);
        }
    }

    public int f(String pref, String suff) {
        List<Integer> list1 = query(prefix, pref);
        if (list1 == null) {
            return -1;
        }
        List<Integer> list2 = query(suffix, new StringBuilder(suff).reverse().toString());
        if (list2 == null) {
            return -1;
        }
        int i = list1.size() - 1, j = list2.size() - 1;
        while (i >= 0 && j >= 0) {
            int idx1 = list1.get(i), idx2 = list2.get(j);
            if (idx1 > idx2) {
                i--;
            } else if (idx1 < idx2) {
                j--;
            } else {
                return idx1;
            }
        }
        return -1;
    }
}


