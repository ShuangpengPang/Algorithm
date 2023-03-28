package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

class Problem1032StreamOfCharacters0 {

    class StreamChecker {

        private final TrieNode root;
        private final char[] stream = new char[40000];
        private int end = -1;

        public StreamChecker(String[] words) {
            root = build(words);
        }

        public TrieNode build(String[] strings) {
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

        private class TrieNode {
            boolean end;
            TrieNode[] children = new TrieNode[26];
        }
    }
}

class Problem1032StreamOfCharacters1 {

    class StreamChecker {

        class Trie {
            Trie[] tries = new Trie[26];
            boolean isWord;
        }

        List<Character> list;
        Trie root;

        private void add(Trie root, String w) {
            Trie trie = root;
            for (int i = w.length() - 1; i >= 0; i--) {
                int j = w.charAt(i) - 'a';
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
            }
            trie.isWord = true;
        }

        public StreamChecker(String[] words) {
            root = new Trie();
            list = new ArrayList<>();
            for (String w : words) {
                add(root, w);
            }
        }

        public boolean query(char letter) {
            list.add(letter);
            Trie trie = root;
            for (int i = list.size() - 1; i >= 0 && trie != null && !trie.isWord; i--) {
                trie = trie.tries[list.get(i) - 'a'];
            }
            return trie != null && trie.isWord;
        }
    }
}

class Problem1032StreamOfCharacters2 {

    class StreamChecker {

        class Trie {
            Trie[] tries = new Trie[26];
            Trie fail;
            boolean isWord;
        }

        Trie root, current;

        public StreamChecker(String[] words) {
            root = new Trie();
            current = root;
            for (String w : words) {
                int n = w.length();
                Trie trie = root;
                for (int i = 0; i < n; i++) {
                    int c = w.charAt(i) - 'a';
                    if (trie.tries[c] == null) {
                        trie.tries[c] = new Trie();
                    }
                    trie = trie.tries[c];
                }
                trie.isWord = true;
            }
            root.fail = root;
            Queue<Trie> q = new LinkedList<>();
            for (int i = 0; i < 26; i++) {
                if (root.tries[i] == null) {
                    root.tries[i] = root;
                } else {
                    root.tries[i].fail = root;
                    q.add(root.tries[i]);
                }
            }
            while (!q.isEmpty()) {
                Trie trie = q.poll(), fail = trie.fail;
                for (int i = 0; i < 26; i++) {
                    if (trie.tries[i] == null) {
                        trie.tries[i] = fail.tries[i];
                    } else {
                        Trie node = trie.tries[i];
                        node.fail = fail.tries[i];
                        node.isWord = node.isWord || fail.tries[i].isWord;
                        q.add(trie.tries[i]);
                    }
                }
            }
        }

        public boolean query(char letter) {
            current = current.tries[letter - 'a'];
            return current.isWord;
        }
    }
}
