package com.shuangpeng.competition.第303场周赛;

/**
 * @Description: Problem2351FirstLetterToAppearTwice（第一个出现两次的字母）
 * @Date 2022/7/29 7:37 PM
 * @Version 1.0
 */
public class Problem2351FirstLetterToAppearTwice {

    // 比赛时写法
    public char repeatedCharacter(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
            if (cnt[c - 'a'] == 2) {
                return c;
            }
        }
        return 'a';
    }
}
