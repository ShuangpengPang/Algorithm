package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3029MinimumTimeToRevertWordToInitialStateI（将单词恢复初始状态所需的最短时间I）
 * @date 2024/2/27 2:31 PM
 */
public class Problem3029MinimumTimeToRevertWordToInitialStateI {

    public int minimumTimeToInitialState(String word, int k) {
        char[] cs = word.toCharArray();
        int n = cs.length;
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && cs[j] != cs[i]) {
                j = next[j - 1];
            }
            next[i] = j = cs[j] == cs[i] ? j + 1 : 0;
        }
        int m = n % k, j = next[n - 1];
        while (j > 0 && j % k != m) {
            j = next[j - 1];
        }
        return (j % k == m ? n - j : n + k - 1) / k;
    }
}
