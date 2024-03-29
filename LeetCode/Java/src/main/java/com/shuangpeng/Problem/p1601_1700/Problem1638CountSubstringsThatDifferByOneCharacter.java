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

class Problem1638CountSubstringsThatDifferByOneCharacter0 {

    public int countSubstrings(String s, String t) {
        int n = s.length(), m = t.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0, d = 0; d <= 1 && i + k < n && j + k < m; k++) {
                    if (s.charAt(i + k) != t.charAt(j + k)) {
                        d++;
                    }
                    if (d == 1) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}

class Problem1638CountSubstringsThatDifferByOneCharacter1 {

    public int countSubstrings0(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] left = new int[m + 2][n + 2], right = new int[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                left[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? left[i - 1][j - 1] + 1 : 0;
            }
        }
        for (int i = m; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                right[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? right[i + 1][j + 1] + 1 : 0;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (left[i][j] == 0) {
                    ans += (left[i - 1][j - 1] + 1) * (right[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }

    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length(), ans = 0;
        int[][] left = new int[m + 1][n + 1], right = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    left[i][j] = left[i - 1][j - 1] + 1;
                }
            }
        }
        for (int i = m; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                if (left[i][j] == 0) {
                    ans += (left[i - 1][j - 1] + 1) * (right[i][j] + 1);
                } else {
                    right[i - 1][j - 1] = right[i][j] + 1;
                }
            }
        }
        return ans;
    }
}

class Problem1638CountSubstringsThatDifferByOneCharacter2 {

    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length(), ans = 0;
        int[][] dp = new int[n + 1][2];
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j][0] = dp[j - 1][0] + 1;
                    dp[j][1] = dp[j - 1][1];
                } else {
                    dp[j][0] = 0;
                    dp[j][1] = dp[j - 1][0] + 1;
                }
                ans += dp[j][1];
            }
        }
        return ans;
    }
}

class Problem1638CountSubstringsThatDifferByOneCharacter3 {

    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length(), ans = 0;
        for (int d = 1 - n; d < m; d++) {
            for (int i = Math.max(d, 0), pp = i - 1, p = pp; i < m && i - d < n; i++) {
                if (s.charAt(i) != t.charAt(i - d)) {
                    pp = p;
                    p = i;
                }
                ans += p - pp;
            }
        }
        return ans;
    }
}
