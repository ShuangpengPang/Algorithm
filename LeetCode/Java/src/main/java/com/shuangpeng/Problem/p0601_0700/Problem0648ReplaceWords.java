package com.shuangpeng.Problem.p0601_0700;

import java.util.List;

/**
 * @Description: Problem0648ReplaceWords（单词替换）
 * @Date 2022/7/7 9:51 AM
 * @Version 1.0
 */
public class Problem0648ReplaceWords {
    class Trie {
        Trie[] tries;
        boolean isWord;

        Trie() {
            tries = new Trie[26];
            isWord = false;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (String s : dictionary) {
            int n = s.length();
            Trie trie = root;
            for (int i = 0; i < n && !trie.isWord; i++) {
                int j = s.charAt(i) - 'a';
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
            }
            trie.isWord = true;
        }
        int n = sentence.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && sentence.charAt(j) != ' ') {
                j++;
            }
            Trie trie = root;
            for (int k = i; k < j && (trie == null || !trie.isWord); k++) {
                char c = sentence.charAt(k);
                sb.append(c);
                if (trie != null) {
                    trie = trie.tries[c - 'a'];
                }
            }
            if (j < n) {
                sb.append(' ');
            }
            j++;
        }
        return sb.toString();
    }
}
