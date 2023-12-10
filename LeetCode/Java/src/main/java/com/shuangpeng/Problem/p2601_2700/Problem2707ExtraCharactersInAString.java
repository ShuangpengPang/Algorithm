package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2707ExtraCharactersInAString（字符串中的额外字符）
 * @date 2023/12/10 11:55 PM
 */
public class Problem2707ExtraCharactersInAString {

    class Trie {
        Trie[] tries = new Trie[26];
        boolean isWord = false;;
    }


    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Trie root = new Trie();
        for (String d : dictionary) {
            add(root, d);
        }
        for (int i = 1; i <= n; i++) {
            Trie trie = root;
            dp[i] = i;
            for (int j = i; j > 0 && dp[i] > 0; j--) {
                if (trie != null) {
                    trie = trie.tries[s.charAt(j - 1) - 'a'];
                }
                dp[i] = Math.min(dp[i], dp[j - 1] + (trie != null && trie.isWord ? 0 : i - j + 1));
            }
        }
        return dp[n];
    }

    private void add(Trie root, String w) {
        Trie trie = root;
        for (int i = w.length() - 1; i >= 0; i--) {
            int c = w.charAt(i) - 'a';
            if (trie.tries[c] == null) {
                trie.tries[c] = new Trie();
            }
            trie = trie.tries[c];
        }
        trie.isWord = true;
    }
}
