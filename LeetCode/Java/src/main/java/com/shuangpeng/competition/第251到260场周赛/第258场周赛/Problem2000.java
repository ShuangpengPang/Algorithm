package com.shuangpeng.competition.第251到260场周赛.第258场周赛;

public class Problem2000 {

    // 比赛时写法
    public String reversePrefix0(String word, char ch) {
        int n = word.length();
        char[] chars = word.toCharArray();
        for (int i = 0; i < n; ++i) {
            if (chars[i] == ch) {
                int l = 0, r = i;
                while (l < r) {
                    char t = chars[l];
                    chars[l] = chars[r];
                    chars[r] = t;
                    l++;
                    r--;
                }
                break;
            }
        }
        return new String(chars);
    }

    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        }
        char[] chars = word.toCharArray();
        int left = 0, right = index;
        while (left < right) {
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
            ++left;
            --right;
        }
        return new String(chars);
    }
}
