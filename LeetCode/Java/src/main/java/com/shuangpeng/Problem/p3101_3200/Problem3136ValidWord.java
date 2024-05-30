package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3136ValidWord（有效单词）
 * @date 2024/5/30 10:38 AM
 */
public class Problem3136ValidWord {

    private static int[] vowel = new int[26];
    static {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for (char c : vowels) {
            vowel[c - 'a'] = 1;
        }
    }

    public boolean isValid(String word) {
        char[] cs = word.toCharArray();
        if (cs.length < 3) {
            return false;
        }
        int mask = 0;
        for (char c : cs) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
            if (Character.isLetter(c)) {
                mask |= 1 << vowel[(c | 32) - 'a'];
            }
        }
        return mask == 3;
    }
}
