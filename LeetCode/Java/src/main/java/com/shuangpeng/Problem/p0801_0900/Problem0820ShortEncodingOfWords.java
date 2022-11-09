package com.shuangpeng.Problem.p0801_0900;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0820ShortEncodingOfWords（单词的压缩编码）
 * @date 2022/11/9 11:36 PM
 */
public class Problem0820ShortEncodingOfWords {

    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>();
        for (String w : words) {
            int m = w.length();
            for (int i = m - 1; i > 0; i--) {
                set.add(w.substring(i));
            }
        }
        int ans = 0;
        for (String w : words) {
            if (set.add(w)) {
                ans += w.length() + 1;
            }
        }
        return ans;
    }
}

class Problem0820ShortEncodingOfWords0 {

    class Trie {
        Trie[] tries = new Trie[26];
    }

    int ans = 0;

    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        for (String w : words) {
            Trie trie = root;
            for (int i = w.length() - 1; i >= 0; i--) {
                int j = w.charAt(i) - 'a';
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
            }
        }
        ans = 0;
        dfs(root, 0);
        return ans;
    }

    private void dfs(Trie root, int depth) {
        boolean isWord = true;
        for (int i = 0; i < 26; i++) {
            if (root.tries[i] != null) {
                isWord = false;
                dfs(root.tries[i], depth + 1);
            }
        }
        if (isWord && depth > 0) {
            ans += depth + 1;
        }
    }
}