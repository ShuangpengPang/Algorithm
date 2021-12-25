package com.shuangpeng.competition.第272场周赛;

public class Problem2109AddingSpacesToAString {

    // 比赛时写法
    public String addSpaces0(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        for (int space : spaces) {
            sb.append(s.substring(pre, space)).append(' ');
            pre = space;
        }
        sb.append(s.substring(pre, s.length()));
        return sb.toString();
    }

    public String addSpaces1(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int m = s.length();
        int n = spaces.length;
        for (int i = 0; i < n; ++i) {
            int pre = i == 0 ? 0 : spaces[i - 1];
            sb.append(s, pre, spaces[i]);
            if (spaces[i] < m) {
                sb.append(' ');
            }
            if (i == n - 1) {
                sb.append(s, spaces[i], m);
            }
        }
        return sb.toString();
    }

    public String addSpaces(String s, int[] spaces) {
        int n = s.length(), m = spaces.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < n; ++i) {
            if (j < m && i == spaces[j]) {
                sb.append(' ');
                ++j;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
