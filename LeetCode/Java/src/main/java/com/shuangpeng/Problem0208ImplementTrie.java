package com.shuangpeng;

public class Problem0208ImplementTrie {

    class Trie {

        class TrieNode {
            int R = 26;
            TrieNode[] nodes;
            boolean isEnd;

            public TrieNode() {
                nodes = new TrieNode[R];
            }

            public boolean isEnd() {
                return isEnd;
            }

            public void setEnd(boolean end) {
                isEnd = end;
            }

            public boolean hasChar(char c) {
                return nodes[c - 'a'] != null;
            }

            public void putChar(char c) {
                nodes[c - 'a'] = new TrieNode();
            }

            public TrieNode getTrieNode(char c) {
                return nodes[c - 'a'];
            }
        }

        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            int length = word.length();
            TrieNode current = root;
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                if (!current.hasChar(c)) {
                    current.putChar(c);
                }
                current = current.getTrieNode(c);
            }
            current.setEnd(true);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = searchWord(word);
            return node != null && node.isEnd();
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return searchWord(prefix) != null;
        }

        public TrieNode searchWord(String word) {
            if (word == null || word.length() == 0) {
                return null;
            }
            int length = word.length();
            TrieNode current = root;
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                if (!current.hasChar(c)) {
                    return null;
                } else {
                    current = current.getTrieNode(c);
                }
            }
            return current;
        }
    }
}
