package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1638CountSubstringsThatDifferByOneCharacter（统计只差一个字符的字串数目）
 * @date 2023/3/27 11:17 AM
 */
public class Problem1638CountSubstringsThatDifferByOneCharacter {

    class Trie {
        Trie[] tries = new Trie[26];
        int count = 0;
    }

    public int countSubstrings(String s, String t) {
        int n = s.length(), m = t.length();
        Trie root = new Trie();
        for (int i = 0; i < m; i++) {
            Trie trie = root;
            for (int j = i; j < m; j++) {
                int c = t.charAt(j) - 'a';
                if (trie.tries[c] == null) {
                    trie.tries[c] = new Trie();
                }
                trie = trie.tries[c];
                trie.count++;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dfs(s, root, i);
        }
        return ans;
    }

    private int dfs(String s, Trie trie, int index) {
        int c = s.charAt(index) - 'a', ans = 0, n = s.length();
        for (int i = 0; i < 26; i++) {
            if (i == c) {
                continue;
            }
            Trie next = trie.tries[i];
            for (int j = index + 1; j < n && next != null; j++) {
                ans += next.count;
                next = next.tries[s.charAt(j) - 'a'];
            }
            if (next != null) {
                ans += next.count;
            }
        }
        if (trie.tries[c] != null && index < n - 1) {
            ans += dfs(s, trie.tries[c], index + 1);
        }
        return ans;
    }
}
