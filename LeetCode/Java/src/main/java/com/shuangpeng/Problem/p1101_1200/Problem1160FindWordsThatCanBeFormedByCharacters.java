package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1160FindWordsThatCanBeFormedByCharacters（拼写单词）
 * @date 2024/1/28 4:31 PM
 */
public class Problem1160FindWordsThatCanBeFormedByCharacters {

    public int countCharacters(String[] words, String chars) {
        int[] cnt = new int[26];
        for (char c : chars.toCharArray()) {
            cnt[c - 'a']++;
        }
        int total = 0;
        for (String w : words) {
            int[] tmp = cnt.clone();
            boolean valid = true;
            for (char c : w.toCharArray()) {
                if (--tmp[c - 'a'] < 0) {
                    valid = false;
                    break;
                }
            }
            total += valid ? w.length() : 0;
        }
        return total;
    }
}
