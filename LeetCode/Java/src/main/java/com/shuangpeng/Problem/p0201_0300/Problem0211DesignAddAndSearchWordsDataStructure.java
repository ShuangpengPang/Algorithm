package com.shuangpeng.Problem.p0201_0300;

import java.util.Arrays;

public class Problem0211DesignAddAndSearchWordsDataStructure {
}

class WordDictionary0 {
    final int N = 26;

    class Trie {
        Trie[] tries;
        boolean isWord;

        Trie() {
            tries = new Trie[N];
            isWord = false;
        }
    }

    Trie root;

    public WordDictionary0() {
        root = new Trie();
    }

    public void addWord(String word) {
        int n = word.length();
        Trie trie = root;
        for (int i = 0; i < n; ++i) {
            int index = word.charAt(i) - 'a';
            if (trie.tries[index] == null) {
                trie.tries[index] = new Trie();
            }
            trie = trie.tries[index];
        }
        trie.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, Trie trie) {
        if (trie == null) {
            return false;
        }
        int n = word.length();
        if (i == n) {
            return trie.isWord;
        }
        char c = word.charAt(i);
        if (c != '.') {
            return dfs(word, i + 1, trie.tries[c - 'a']);
        } else {
            for (int j = 0; j < N; ++j) {
                if (dfs(word, i + 1, trie.tries[j])) {
                    return true;
                }
            }
            return false;
        }
    }
}

class WordDictionary {
    static final int S = 26;
    static final int N = 250000;
    static int[][] tries = new int[N][S];
    static int idx;
    static boolean[] isWords = new boolean[N];

    public WordDictionary() {
        for (int i = 0; i < N; ++i) {
            Arrays.fill(tries[i], 0);
        }
        Arrays.fill(isWords, false);
        idx = 0;
    }

    public void addWord(String word) {
        int n = word.length();
        int p = 0;
        for (int i = 0; i < n; ++i) {
            int index = word.charAt(i) - 'a';
            if (tries[p][index] == 0) {
                tries[p][index] = ++idx;
            }
            p = tries[p][index];
        }
        isWords[p] = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, 0);
    }

    private boolean dfs(String word, int i, int p) {
        int n = word.length();
        if (i == n) {
            return isWords[p];
        }
        char c = word.charAt(i);
        if (c != '.') {
            return tries[p][c - 'a'] != 0 && dfs(word, i + 1, tries[p][c - 'a']);
        } else {
            for (int j = 0; j < S; ++j) {
                if (tries[p][j] != 0 && dfs(word, i + 1, tries[p][j])) {
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
