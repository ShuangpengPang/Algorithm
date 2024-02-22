package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3043FindTheLengthOfTheLongestCommonPrefix（最长公共前缀的长度）
 * @date 2024/2/22 5:18 PM
 */
public class Problem3043FindTheLengthOfTheLongestCommonPrefix {

    class Trie {
        Trie[] tries = new Trie[10];
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie root = new Trie();
        for (int num : arr1) {
            Trie trie = root;
            for (char c : Integer.toString(num).toCharArray()) {
                int i = c - '0';
                if (trie.tries[i] == null) {
                    trie.tries[i] = new Trie();
                }
                trie = trie.tries[i];
            }
        }
        int ans = 0;
        for (int num : arr2) {
            Trie trie = root;
            int length = 0;
            for (char c : Integer.toString(num).toCharArray()) {
                int i = c - '0';
                if (trie.tries[i] != null) {
                    trie = trie.tries[i];
                    length++;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, length);
        }
        return ans;
    }
}
