package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR064MagicDictionary（实现一个魔法字典）
 * @date 2024/6/26 10:44 AM
 */
public class LCR064MagicDictionary {

    class MagicDictionary {

        private String[] dictionary;

        /** Initialize your data structure here. */
        public MagicDictionary() {

        }

        public void buildDict(String[] dictionary) {
            this.dictionary = dictionary;
        }

        public boolean search(String searchWord) {
            int n = searchWord.length();
            for (String word : dictionary) {
                if (word.length() == n) {
                    int cnt = 0;
                    for (int i = 0; i < n && cnt < 2; i++) {
                        if (word.charAt(i) != searchWord.charAt(i)) {
                            cnt++;
                        }
                    }
                    if (cnt == 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
}

class LCR064MagicDictionary0 {

    class MagicDictionary {

        class Trie {
            Trie[] tries = new Trie[26];
            boolean isWord = false;
        }

        private Trie root;

        /** Initialize your data structure here. */
        public MagicDictionary() {
            root = new Trie();
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                Trie trie = root;
                for (char c : word.toCharArray()) {
                    if (trie.tries[c - 'a'] == null) {
                        trie.tries[c - 'a'] = new Trie();
                    }
                    trie = trie.tries[c - 'a'];
                }
                trie.isWord = true;
            }
        }

        public boolean search(String searchWord) {
            return dfs(searchWord.toCharArray(), root, 0, false);
        }

        private boolean dfs(char[] cs, Trie trie, int pos, boolean modified) {
            if (pos == cs.length) {
                return modified && trie.isWord;
            }
            int c = cs[pos] - 'a';
            if (trie.tries[c] != null && dfs(cs, trie.tries[c], pos + 1, modified)) {
                return true;
            }
            if (!modified) {
                for (int i = 0; i < 26; i++) {
                    if (i != c && trie.tries[i] != null && dfs(cs, trie.tries[i], pos + 1, true)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
