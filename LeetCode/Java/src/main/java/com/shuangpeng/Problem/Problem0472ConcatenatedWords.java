package com.shuangpeng.Problem;

import java.util.*;

public class Problem0472ConcatenatedWords {

    class Trie {
        boolean flag;
        Map<Character, Trie> trieMap;

        public Trie() {
            trieMap = new HashMap<>();
            flag = false;
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        int n = words.length;
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (word.isEmpty()) {
                continue;
            }
            Trie trie = root;
            int size = word.length();
            for (int j = 0; j < size; j++) {
                char c = word.charAt(j);
                Map<Character, Trie> map = trie.trieMap;
                if (!map.containsKey(c)) {
                    map.put(c, new Trie());
                }
                trie = map.get(c);
            }
            trie.flag = true;
        }
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!words[i].isEmpty() && backtrack(words[i], root, 0, false)) {
                answer.add(words[i]);
            }
        }
        return answer;
    }

    private boolean backtrack(String word, Trie root, int start, boolean valid) {
        int n = word.length();
        Trie trie = root;
        for (int i = start; i < n; i++) {
            Map<Character, Trie> map = trie.trieMap;
            char c = word.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            trie = map.get(c);
            if (trie.flag && backtrack(word, root, i + 1, true)) {
                return true;
            }
        }
        return trie.flag && valid;
    }

//    public static void main(String[] args) {
//        Problem0472ConcatenatedWords a = new Problem0472ConcatenatedWords();
//        a.findAllConcatenatedWordsInADict(new String[]{"q", ""});
//    }
}
