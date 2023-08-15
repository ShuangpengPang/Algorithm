package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2697LexicographicallySmallestPalindrome（字典序最小回文串）
 * @date 2023/8/15 2:02 PM
 */
public class Problem2697LexicographicallySmallestPalindrome {

    public String makeSmallestPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            if (cs[i] < cs[j]) {
                cs[j] = cs[i];
            } else if (cs[i] > cs[j]) {
                cs[i] = cs[j];
            }
        }
        return new String(cs);
    }
}
