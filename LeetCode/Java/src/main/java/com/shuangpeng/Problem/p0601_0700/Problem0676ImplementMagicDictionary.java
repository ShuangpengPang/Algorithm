package com.shuangpeng.Problem.p0601_0700;

import java.util.*;

/**
 * @Description: Problem0676ImplementMagicDictionary（实现一个魔法字典）
 * @Date 2022/7/11 9:57 AM
 * @Version 1.0
 */
public class Problem0676ImplementMagicDictionary {

    static class MagicDictionary {

        static int index = 0;
        static int N = (int) 1e4 + 1;
        static int[][] tr = new int[N][26];
        static boolean[] isWord = new boolean[N];

        public MagicDictionary() {
            for (int i = 0; i <= index; i++) {
                Arrays.fill(tr[i], 0);
                isWord[i] = false;
            }
            index = 0;
        }

        public void buildDict(String[] dictionary) {
            for (String d : dictionary) {
                int n = d.length();
                int p = 0;
                for (int i = 0; i < n; i++) {
                    int j = d.charAt(i) - 'a';
                    if (tr[p][j] == 0) {
                        tr[p][j] = ++index;
                    }
                    p = tr[p][j];
                }
                isWord[p] = true;
            }
        }

        public boolean search(String searchWord) {
            int n = searchWord.length();
            int p = 0;
            for (int i = 0; i < n; i++) {
                int j = searchWord.charAt(i) - 'a';
                for (int k = 0; k < 26; k++) {
                    if (k == j || tr[p][k] == 0) {
                        continue;
                    }
                    if (check(searchWord, tr[p][k], i + 1)) {
                        return true;
                    }
                }
                if (tr[p][j] == 0) {
                    return false;
                }
                p = tr[p][j];
            }
            return false;
        }

        private boolean check(String s, int p, int i) {
            int n = s.length();
            while (i < n) {
                int j = s.charAt(i) - 'a';
                if (tr[p][j] == 0) {
                    return false;
                }
                p = tr[p][j];
                i++;
            }
            return isWord[p];
        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
}

class Problem0676ImplementMagicDictionary0 {

    class MagicDictionary {

        Map<Integer, List<String>> map = new HashMap<>(100);

        public MagicDictionary() {
            map.clear();
        }

        public void buildDict(String[] dictionary) {
            for (String d : dictionary) {
                int n = d.length();
                map.putIfAbsent(n, new ArrayList<>());
                map.get(n).add(d);
            }
        }

        public boolean search(String searchWord) {
            int n = searchWord.length();
            if (!map.containsKey(n)) {
                return false;
            }
            for (String s : map.get(n)) {
                if (check(s, searchWord)) {
                    return true;
                }
            }
            return false;
        }

        private boolean check(String s1, String s2) {
            int n = s1.length();
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    cnt++;
                    if (cnt > 1) {
                        return false;
                    }
                }
            }
            return cnt == 1;
        }
    }
}

class Problem0676ImplementMagicDictionary1 {

    class MagicDictionary {

        class Trie {
            Trie[] tries;
            boolean isWord;

            Trie() {
                tries = new Trie[26];
            }
        }

        Trie root;

        public MagicDictionary() {
            root = new Trie();
        }

        public void buildDict(String[] dictionary) {
            for (String dict : dictionary) {
                Trie trie = root;
                int n = dict.length();
                for (int i = 0; i < n; i++) {
                    int j = dict.charAt(i) - 'a';
                    if (trie.tries[j] == null) {
                        trie.tries[j] = new Trie();
                    }
                    trie = trie.tries[j];
                }
                trie.isWord = true;
            }
        }

        public boolean search(String searchWord) {
            return dfs(searchWord, root, 0, false);
        }

        private boolean dfs(String searchWord, Trie trie, int pos, boolean isModified) {
            if (pos == searchWord.length()) {
                return isModified && trie.isWord;
            }
            int c = searchWord.charAt(pos) - 'a';
            if (trie.tries[c] != null && dfs(searchWord, trie.tries[c], pos + 1, isModified)) {
                return true;
            }
            if (!isModified) {
                for (int i = 0; i < 26; i++) {
                    if (i == c || trie.tries[i] == null) {
                        continue;
                    }
                    if (dfs(searchWord, trie.tries[i], pos + 1, true)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}