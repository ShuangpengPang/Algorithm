package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1897RedistributeCharactersToMakeAllStringsEqual（重新分配字符使所有字符串都相等）
 * @date 2024/3/21 2:40 PM
 */
public class Problem1897RedistributeCharactersToMakeAllStringsEqual {

    public boolean makeEqual(String[] words) {
        int[] cnt = new int[26];
        for (String w : words) {
            for (char c : w.toCharArray()) {
                cnt[c - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if ((cnt[i] % words.length) != 0) {
                return false;
            }
        }
        return true;
    }
}
