package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR065MinimumLengthEncoding（单词的压缩编码）
 * @date 2024/6/27 11:28 AM
 */
public class LCR065MinimumLengthEncoding {

    class Trie {
        Trie[] tries = new Trie[26];
        boolean isEnd = false;
    }

    public int minimumLengthEncoding(String[] words) {
        int ans = 0;
        Trie root = new Trie();
        for (String w : words) {
            Trie trie = root;
            char[] cs = w.toCharArray();
            int n = cs.length, lastEnd = -2;
            boolean hasAdd = false;
            for (int i = n - 1; i >= 0; i--) {
                int c = cs[i] - 'a';
                if (trie.tries[c] == null) {
                    hasAdd = true;
                    trie.tries[c] = new Trie();
                }
                trie = trie.tries[c];
                if (trie.isEnd) {
                    trie.isEnd = false;
                    lastEnd = n - i - 1;
                }
            }
            if (hasAdd || lastEnd == n - 1) {
                trie.isEnd = true;
                ans += n - lastEnd - 1;
            }
        }
        return ans;
    }
}
