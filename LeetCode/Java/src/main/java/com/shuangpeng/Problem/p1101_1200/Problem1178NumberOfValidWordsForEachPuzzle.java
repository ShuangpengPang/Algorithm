package com.shuangpeng.Problem.p1101_1200;

import java.util.*;

/**
 * @Description: Problem1178NumberOfValidWordsForEachPuzzle（猜字谜）
 * @Date 2022/7/12 5:00 PM
 * @Version 1.0
 */
public class Problem1178NumberOfValidWordsForEachPuzzle {

    class Trie {
        Trie[] tries = new Trie[26];
        int count = 0;
    }

    Trie root;

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        root = new Trie();
        for (String w : words) {
            char[] chars = w.toCharArray();
            Arrays.sort(chars);
            int m = w.length();
            Trie trie = root;
            for (int i = 0; i < m; i++) {
                int j = chars[i] - 'a';
                if (i > 0 && chars[i] == chars[i - 1]) {
                    continue;
                }
                if (trie.tries[j] == null) {
                    trie.tries[j] = new Trie();
                }
                trie = trie.tries[j];
            }
            trie.count++;
        }
        List<Integer> ans = new ArrayList<>(puzzles.length);
        for (String p : puzzles) {
            char[] chars = p.toCharArray();
            char ch = chars[0];
            Arrays.sort(chars);
            int n = chars.length;
            int idx = 0;
            while (idx < n && chars[idx] != ch) {
                idx++;
            }
            int M = 1 << n;
            int count = 0;
            for (int m = 1; m < M; m++) {
                if (((m >> idx) & 1) != 0) {
                    Trie trie = root;
                    boolean valid = true;
                    for (int i = 0; i < n; i++) {
                        if (((m >> i) & 1) == 1) {
                            int j = chars[i] - 'a';
                            if (trie.tries[j] == null) {
                                valid = false;
                                break;
                            }
                            trie = trie.tries[j];
                        }
                    }
                    if (valid) {
                        count += trie.count;
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}

class Problem1178NumberOfValidWordsForEachPuzzle0 {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
         Map<Integer, Integer> map = new HashMap<>();
         for (String w : words) {
             int num = 0;
             int m = w.length();
             for (int i = 0; i < m; i++) {
                 num |= 1 << (w.charAt(i) - 'a');
             }
             map.put(num, map.getOrDefault(num, 0) + 1);
         }
         int n = puzzles[0].length();
         int N = 1 << n;
         List<Integer> ans = new ArrayList<>(puzzles.length);
         for (String p : puzzles) {
             int count = 0;
             for (int m = 1; m < N; m += 2) {
                 int num = 0;
                 for (int i = 0; i < n; i++) {
                     if (((m >> i) & 1) == 1) {
                         num |= 1 << (p.charAt(i) - 'a');
                     }
                 }
                 count += map.getOrDefault(num, 0);
             }
             ans.add(count);
         }
         return ans;
    }
}

class Problem1178NumberOfValidWordsForEachPuzzle1 {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String w : words) {
            int num = 0;
            int n = w.length();
            for (int i = 0; i < n; i++) {
                num |= (1 << (w.charAt(i) - 'a'));
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>(puzzles.length);
        for (String p : puzzles) {
            int n = p.length();
            int mask = 0;
            for (int i = 1; i < n; i++) {
                mask |= 1 << (p.charAt(i) - 'a');
            }
            int subset = mask;
            int first = 1 << (p.charAt(0) - 'a');
            int count = 0;
            do {
                int s = subset | first;
                count += map.getOrDefault(s, 0);
                subset = (subset - 1) & mask;
            } while (subset != mask);
            ans.add(count);
        }
        return ans;
    }
}

class Problem1178NumberOfValidWordsForEachPuzzle2 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
            this.val = 0;
            this.left = null;
            this.right = null;
        }
    }

    class Solution {

        int findAnswer(TreeNode cur, boolean[] map, int index, int firstIndex) {
            if (cur == null || index == map.length) {
                return cur == null ? 0 : cur.val;
            }

            if (!map[index]) {
                return findAnswer(cur.left, map, index + 1, firstIndex);
            } else if (firstIndex == index) {
                return findAnswer(cur.right, map, index + 1, firstIndex);
            } else {
                return findAnswer(cur.left, map, index + 1, firstIndex) + findAnswer(cur.right, map, index + 1, firstIndex);
            }
        }

        public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
            int n = words.length, m = puzzles.length;
            TreeNode root = new TreeNode();
            for (int i = 0; i < n; i++) {
                boolean[] map = new boolean[26];
                Arrays.fill(map, false);
                for (int j = 0; j < words[i].length(); j++) {
                    map[words[i].charAt(j) - 'a'] = true;
                }
                TreeNode cur = root;
                for (int j = 0; j < map.length; j++) {
                    TreeNode next;
                    if (!map[j]) {
                        next = (cur.left == null ? new TreeNode() : cur.left);
                        cur.left = next;
                    } else {
                        next = (cur.right == null ? new TreeNode() : cur.right);
                        cur.right = next;
                    }
                    cur = next;
                    cur.val += 1;
                }
            }

            List<Integer> ans = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                boolean[] map = new boolean[26];
                Arrays.fill(map, false);
                for (int j = 0; j < puzzles[i].length(); j++) {
                    map[puzzles[i].charAt(j) - 'a'] = true;
                }
                ans.add(findAnswer(root, map, 0, puzzles[i].charAt(0) - 'a'));
            }

            return ans;
        }
    }
}

