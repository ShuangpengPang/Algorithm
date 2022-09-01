package com.shuangpeng.competition.双周赛.第059场双周赛;

public class Problem1974 {

    public int minTimeToType(String word) {
        int n = word.length();
        int count = 0;
        char c = 'a';
        for (int i = 0; i < n; ++i) {
            char ch = word.charAt(i);
            int j = Math.abs(ch - c);
            count += Math.min(j, 26 - j) + 1;
            c = ch;
        }
        return count;
    }
}
