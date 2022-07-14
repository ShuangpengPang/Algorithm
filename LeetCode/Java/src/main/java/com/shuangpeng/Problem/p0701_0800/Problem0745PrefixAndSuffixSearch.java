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

class Problem0745PrefixAndSuffixSearch0 {

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
}

class Problem0745PrefixAndSuffixSearch1 {

    class WordFilter {

        Map<String, Integer> dictionary;

        public WordFilter(String[] words) {
            int n = words.length;
            dictionary = new HashMap<String, Integer>();
            for (int i = 0; i < n; i++) {
                String w = words[i];
                int m = w.length();
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < m; k++) {
                        dictionary.put(w.substring(0, j + 1) + "#" + w.substring(k), i);
                    }
                }
            }
        }

        public int f(String pref, String suff) {
            return dictionary.getOrDefault(pref + "#" + suff, -1);
        }
    }
}

class Problem0745PrefixAndSuffixSearch2 {

    class WordFilter {

        class Trie {
            Map<String, Trie> child = new HashMap<>();
            int index;
        }

        Trie root;

        public WordFilter(String[] words) {
            root = new Trie();
            int n = words.length;
            for (int i = 0; i < n; i++) {
                String w = words[i];
                int m = w.length();
                Trie trie = root;
                for (int j = 0; j < m; j++) {
                    Trie tmp = trie;
                    for (int k = j; k < m; k++) {
                        String key = "" + w.charAt(k) + '#';
                        if (!tmp.child.containsKey(key)) {
                            tmp.child.put(key, new Trie());
                        }
                        tmp = tmp.child.get(key);
                        tmp.index = i;
                    }
                    tmp = trie;
                    for (int k = m - j - 1; k >= 0; k--) {
                        String key = "#" + w.charAt(k);
                        if (!tmp.child.containsKey(key)) {
                            tmp.child.put(key, new Trie());
                        }
                        tmp = tmp.child.get(key);
                        tmp.index = i;
                    }
                    String key = "" + w.charAt(j) + w.charAt(m - j - 1);
                    if (!trie.child.containsKey(key)) {
                        trie.child.put(key, new Trie());
                    }
                    trie = trie.child.get(key);
                    trie.index = i;
                }
            }
        }

        public int f(String pref, String suff) {
            int n1 = pref.length(), n2 = suff.length();
            int n = Math.max(n1, n2);
            Trie trie = root;
            for (int i = 0; i < n; i++) {
                char c1 = i < n1 ? pref.charAt(i) : '#';
                char c2 = i < n2 ? suff.charAt(n2 - i - 1) : '#';
                String key = "" + c1 + c2;
                if (!trie.child.containsKey(key)) {
                    return -1;
                }
                trie = trie.child.get(key);
            }
            return trie.index;
        }
    }
}

class Problem0745PrefixAndSuffixSearch3 {

    class WordFilter {

        Map<String, List<Integer>> map;
        String[] words;

        public WordFilter(String[] words) {
            map = new HashMap<>();
            this.words = words;
            int n = words.length;
            for (int i = 0; i < n; i++) {
                String w = words[i];
                int m = w.length();
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(w.charAt(j));
                    String s = sb.toString();
                    if (!map.containsKey(s)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        map.put(s, list);
                    } else {
                        map.get(s).add(i);
                    }
                }
            }
        }

        public int f(String pref, String suff) {
            List<Integer> list = map.get(pref);
            if (list == null) {
                return -1;
            }
            int n = list.size();
            for (int i = n - 1; i >= 0; i--) {
                int idx = list.get(i);
                String w = words[idx];
                int m = w.length();
                if (m < suff.length()) {
                    continue;
                }
                int i1 = m - 1, i2 = suff.length() - 1;
                boolean find = true;
                while (i2 >= 0) {
                    if (w.charAt(i1--) != suff.charAt(i2--)) {
                        find = false;
                        break;
                    }
                }
                if (find) {
                    return idx;
                }
            }
            return -1;
        }
    }
}


