package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;

/**
 * @Description: Problem0676ImplementMagicDictionary（实现一个魔法字典）
 * @Date 2022/7/11 9:57 AM
 * @Version 1.0
 */
public class Problem0676ImplementMagicDictionary {

    static class MagicDictionary {

        static int index = 0;
        static int N = (int) 1e4 + 1;
        static int[][] tr = new int[N][26];
        static boolean[] isWord = new boolean[N];

        public MagicDictionary() {
            for (int i = 0; i <= index; i++) {
                Arrays.fill(tr[i], 0);
                isWord[i] = false;
            }
            index = 0;
        }

        public void buildDict(String[] dictionary) {
            for (String d : dictionary) {
                int n = d.length();
                int p = 0;
                for (int i = 0; i < n; i++) {
                    int j = d.charAt(i) - 'a';
                    if (tr[p][j] == 0) {
                        tr[p][j] = ++index;
                    }
                    p = tr[p][j];
                }
                isWord[p] = true;
            }
        }

        public boolean search(String searchWord) {
            int n = searchWord.length();
            int p = 0;
            for (int i = 0; i < n; i++) {
                int j = searchWord.charAt(i) - 'a';
                for (int k = 0; k < 26; k++) {
                    if (k == j || tr[p][k] == 0) {
                        continue;
                    }
                    if (check(searchWord, tr[p][k], i + 1)) {
                        return true;
                    }
                }
                if (tr[p][j] == 0) {
                    return false;
                }
                p = tr[p][j];
            }
            return false;
        }

        private boolean check(String s, int p, int i) {
            int n = s.length();
            while (i < n) {
                int j = s.charAt(i) - 'a';
                if (tr[p][j] == 0) {
                    return false;
                }
                p = tr[p][j];
                i++;
            }
            return isWord[p];
        }
    }

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
}

//class MagicDictionary {
//
//    Map<Integer, List<String>> map;
//
//    public MagicDictionary() {
//    }
//
//    public void buildDict(String[] dictionary) {
//    }
//
//    public boolean search(String searchWord) {
//    }
//}

