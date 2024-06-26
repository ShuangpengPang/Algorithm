package com.shuangpeng.lcr.p001_100;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR063ReplaceWords
 * @date 2024/6/26 12:13 PM
 */
public class LCR063ReplaceWords {

    class Trie {
        Trie[] tries = new Trie[26];
        boolean isWord = false;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (String dict : dictionary) {
            Trie trie = root;
            for (int i = 0, n = dict.length(); i < n && !trie.isWord; i++) {
                int c = dict.charAt(i) - 'a';
                if (trie.tries[c] == null) {
                    trie.tries[c] = new Trie();
                }
                trie = trie.tries[c];
            }
            trie.isWord = true;
        }
        StringBuilder sb = new StringBuilder();
        Trie trie = root;
        for (char c : sentence.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                trie = root;
            } else if (trie == null || !trie.isWord) {
                if (trie != null) {
                    trie = trie.tries[c - 'a'];
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
