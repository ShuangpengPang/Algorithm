package com.shuangpeng.competition.第063场双周赛;

public class Problem2038 {

    // 比赛时代码
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        int a = 0, b = 0;
        int count1 = 0, count2 = 0;
        char c = ' ';
        for (int i = 0; i < n; ++i) {
            char ch = colors.charAt(i);
            if (ch == c) {
                if (ch == 'A') {
                    ++a;
                } else {
                    ++b;
                }
            } else {
                if (ch == 'A') {
                    count2 += Math.max(b - 2, 0);
                    b = 0;
                    a = 1;
                    c = 'A';
                } else {
                    count1 += Math.max(a - 2, 0);
                    a = 0;
                    b = 1;
                    c = 'B';
                }
            }
        }
        if (c == 'A') {
            count1 += Math.max(a - 2, 0);
        } else {
            count2 += Math.max(b - 2, 0);
        }
        return count1 > count2;
    }
}
