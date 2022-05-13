package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1032StreamOfCharacters（字符流）
 * @Date 2022/5/13 12:07 PM
 * @Version 1.0
 */
public class Problem1032StreamOfCharacters {

    class Trie {
        Trie[] tries;
        boolean isWord;

        Trie() {
            tries = new Trie[26];
            isWord = false;
        }
    }

    class StreamChecker {

        Trie root;
        List<Character> list;

        public StreamChecker(String[] words) {
            root = new Trie();
            list = new ArrayList<>();
            for (String w : words) {
                Trie trie = root;
                int n = w.length();
                for (int i = n - 1; i >= 0; --i) {
                    int j = w.charAt(i) - 'a';
                    if (trie.tries[j] == null) {
                        trie.tries[j] = new Trie();
                    }
                    trie = trie.tries[j];
                }
                trie.isWord = true;
            }
        }

        public boolean query(char letter) {
            list.add(letter);
            Trie trie = root;
            for (int i = list.size() - 1; i >= 0; --i) {
                int j = list.get(i) - 'a';
                trie = trie.tries[j];
                if (trie == null) {
                    return false;
                }
                if (trie.isWord) {
                    return true;
                }
            }
            return false;
        }
    }

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
}


class StreamChecker {

    private final TrieNode root;
    private final char[] stream = new char[40000];
    private int end = -1;

    public StreamChecker(String[] words) {
        root = build(words);
    }

    public static TrieNode build(String[] strings) {
        TrieNode root = new TrieNode();
        for (String string : strings) {
            TrieNode node = root;
            char[] chars = string.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                char c = chars[i];
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.end = true;
        }
        return root;
    }

    public boolean query(char letter) {
        stream[++end] = letter;
        return query(end, root);
    }

    private boolean query(int index, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (node.end) {
            return true;
        }
        if (index < 0) {
            return false;
        }
        return query(index - 1, node.children[stream[index] - 'a']);
    }

    private static class TrieNode {
        boolean end;
        TrieNode[] children = new TrieNode[26];
    }
}
