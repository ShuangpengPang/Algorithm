package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR062Trie（实现 Trie (前缀树)）
 * @date 2024/6/26 2:39 PM
 */
public class LCR062Trie {

    class Trie {

        private Trie[] tries;
        private boolean isWord;

        /** Initialize your data structure here. */
        public Trie() {
            tries = new Trie[26];
            isWord = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Trie trie = this;
            for (char ch : word.toCharArray()) {
                int c = ch - 'a';
                if (trie.tries[c] == null) {
                    trie.tries[c] = new Trie();
                }
                trie = trie.tries[c];
            }
            trie.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Trie trie = getTrie(word);
            return trie != null && trie.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return getTrie(prefix) != null;
        }

        private Trie getTrie(String word) {
            Trie trie = this;
            for (char ch : word.toCharArray()) {
                int c = ch - 'a';
                if (trie.tries[c] == null) {
                    return null;
                }
                trie = trie.tries[c];
            }
            return trie;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
