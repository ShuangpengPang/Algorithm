package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3291MinimumNumberOfValidStringsToFormTargetI（形成目标字符串需要的最少字符串数I）
 * @date 2024/11/28 6:58 PM
 */
public class Problem3291MinimumNumberOfValidStringsToFormTargetI {

    class Trie {
        Trie[] tries = new Trie[26];
    }

    public int minValidStrings(String[] words, String target) {
        Trie root = new Trie();
        for (String w : words) {
            Trie trie = root;
            for (char ch : w.toCharArray()) {
                int c = ch - 'a';
                if (trie.tries[c] == null) {
                    trie.tries[c] = new Trie();
                }
                trie = trie.tries[c];
            }
        }
        char[] cs = target.toCharArray();
        int n = cs.length, N = n + 1;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            Trie trie = root;
            dp[i] = N;
            for (int j = i; j < n; j++) {
                int c = cs[j] - 'a';
                if (trie.tries[c] == null) {
                    break;
                }
                trie = trie.tries[c];
                dp[i] = Math.min(dp[i], dp[j + 1] + 1);
            }
        }
        return dp[0] == N ? -1 : dp[0];
    }
}
