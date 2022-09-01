package com.shuangpeng.competition.双周赛.第058场双周赛;

public class Problem1957 {

    public String makeFancyString0(String s) {
        StringBuilder sb = new StringBuilder();
        char ch = ' ';
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c != ch) {
                ch = c;
                count = 1;
                sb.append(c);
            } else if (count < 2) {
                sb.append(c);
                count++;
            }
        }
        return sb.toString();
    }

    public String makeFancyString(String s) {
        int n = s.length();
        if (n <= 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        sb.append(s.charAt(1));
        for (int i = 2; i < n; ++i) {
            char c1 = s.charAt(i - 2);
            char c2 = s.charAt(i - 1);
            char c = s.charAt(i);
            if (c != c1 || c != c2) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
